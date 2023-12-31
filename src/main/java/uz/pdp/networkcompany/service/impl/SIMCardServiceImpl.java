package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.networkcompany.dto.request.*;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.*;
import uz.pdp.networkcompany.entity.Package;
import uz.pdp.networkcompany.enums.PaymentType;
import uz.pdp.networkcompany.enums.ServiceType;
import uz.pdp.networkcompany.mapper.SIMCardMapper;
import uz.pdp.networkcompany.repository.SIMCardRepository;
import uz.pdp.networkcompany.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class SIMCardServiceImpl implements SIMCardService {
    @Autowired
    private SIMCardRepository simCardRepository;
    @Autowired
    private PassportService passportService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SIMCardMapper simCardMapper;
    private final String existsByNumber = "SIMCard with number = %s already exists";
    private final String notFoundById = "SIMCard with id = %d not found";
    private final String notFoundByUsername = "SIMCard with username = %s not found";
    private final String notFoundByNumber = "SIMCard with number = %d not found";
    private final String alreadyHasClient = "SIMCard with id = %d already has a client";
    private final String noHolderById = "SIMCard with id = %d no holder";
    private final String notEnoughBalanceById = "SIMCard with id = %d not enough balance. Required balance is %f";
    private final String tariffAndClientTypesNotMatch = "Tariff and Client types did not match. This tariff only for %s clients";

    @Override
    public Page<SIMCardView> getAll(Pageable pageable) {
        return simCardRepository.findAll(pageable).map(s -> simCardMapper.mapToSIMCardView(s));
    }

    @Override
    public SIMCardView getById(Long id) {
        return simCardMapper.mapToSIMCardView(findById(id));
    }

    @Override
    public SIMCardView create(SIMCardRequest request) {
        if (simCardRepository.existsByNumber(request.getNumber())) {
            throw new EntityExistsException(String.format(existsByNumber, request.getNumber()));
        }
        SIMCard simCard = new SIMCard();

        if (request.getNumber() != null) {
            simCard.setNumber(request.getNumber());
        }

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Override
    public SIMCardView update(SIMCardRequest request, Long id) {
        if (simCardRepository.existsByNumberAndIdNot(request.getNumber(), id)) {
            throw new EntityExistsException(String.format(existsByNumber, request.getNumber()));
        }
        SIMCard simCard = findById(id);

        if (request.getNumber() != null) {
            simCard.setNumber(request.getNumber());
        }

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Transactional
    @Override
    public SIMCardView setTariff(SetTariffRequest request, Long id) {
        SIMCard simCard = findById(id);
        Tariff tariff = tariffService.findById(request.getTariffId());

        SIMCard saved = setTariff(simCard, tariff);

        return simCardMapper.mapToSIMCardView(saved);
    }

    @Transactional
    @Override
    public String setTariff(String username, String code) {
        SIMCard simCard = findByUsername(username);
        Tariff tariff = tariffService.findByUSSDCode(code);

        setTariff(simCard, tariff);

        return String.format("Tariff - %s set successfully", tariff.getName());
    }

    private SIMCard setTariff(SIMCard simCard, Tariff tariff) {
        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, simCard.getId()), null);
        }
        if (!simCard.getPassport().getClient().getType().equals(tariff.getType())) {
            throw new EntityActionVetoException(String.format(tariffAndClientTypesNotMatch, tariff.getType()), null);
        }
        Double priceWithConnection = tariff.getConnectionPrice() + calcPriceForCurrentMonth(tariff.getPrice());
        if (simCard.getBalance() < priceWithConnection) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, simCard.getId(), priceWithConnection), null);
        }

        simCard.setBalance(simCard.getBalance() - priceWithConnection);
        simCard.setMinuteLimit(calcLimitForCurrentMonth(tariff.getPerMonthMinuteLimit()));
        simCard.setMbLimit(calcLimitForCurrentMonth(tariff.getPerMonthMBLimit()));
        simCard.setSmsLimit(calcLimitForCurrentMonth(tariff.getPerMonthSMSLimit()));
        simCard.setActive(true);
        simCard.setTariff(tariff);

        paymentService.create(priceWithConnection, simCard, PaymentType.TARIFF);

        return save(simCard);
    }

    @Override
    public SIMCardView setPassport(SetPassportRequest request, Long id) {
        SIMCard simCard = findById(id);

        if (simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(alreadyHasClient, id), null);
        }

        Passport passport = passportService.findById(request.getPassportId());

        simCard.setPassport(passport);

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Transactional
    @Override
    public SIMCardView addBalance(AddAmountRequest request, Long id) {
        SIMCard simCard = findById(id);

        SIMCard saved = addBalance(simCard, request.getAmount());

        return simCardMapper.mapToSIMCardView(saved);
    }

    @Transactional
    @Override
    public String addBalance(AddAmountRequest request, String username) {
        SIMCard simCard = findByUsername(username);

        SIMCard saved = addBalance(simCard, request.getAmount());

        return String.format("Amount added successfully to balance. Current balance - %f", saved.getBalance());
    }

    private SIMCard addBalance(SIMCard simCard, Double amount) {
        simCard.setBalance(simCard.getBalance() + amount);

        paymentService.create(amount, simCard, PaymentType.BALANCE);

        return save(simCard);
    }

    @Transactional
    @Override
    public SIMCardView addService(AddServiceRequest request, Long id) {
        SIMCard simCard = findById(id);
        Service service = serviceService.findById(request.getServiceId());

        SIMCard saved = addService(simCard, service);

        return simCardMapper.mapToSIMCardView(saved);
    }

    @Transactional
    @Override
    public String addService(String username, String code) {
        SIMCard simCard = findByUsername(username);
        Service service = serviceService.findByUSSDCode(code);

        addService(simCard, service);

        return String.format("Service - %s added successfully", service.getName());
    }

    private SIMCard addService(SIMCard simCard, Service service) {
        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, simCard.getId()), null);
        }
        double price = service.getPrice();
        if (service.getType().equals(ServiceType.MONTHLY)) {
            price = calcPriceForCurrentMonth(price);
        }
        if (simCard.getBalance() < price) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, simCard.getId(), price), null);
        }

        simCard.setBalance(simCard.getBalance() - price);
        simCard.addService(service);

        paymentService.create(price, simCard, PaymentType.SERVICE);

        return save(simCard);
    }

    private Double calcPriceForCurrentMonth(Double price) {
        LocalDate date = LocalDate.now();
        int lengthOfMonth = date.getMonth().length(date.isLeapYear());
        int remainingDays = lengthOfMonth - date.getDayOfMonth();
        double perDayPrice = price / lengthOfMonth;

        if (remainingDays == 0) {
            remainingDays = 1;
        }

        return perDayPrice * remainingDays;
    }
    private Integer calcLimitForCurrentMonth(Integer limit) {
        LocalDate date = LocalDate.now();
        int lengthOfMonth = date.getMonth().length(date.isLeapYear());
        int remainingDays = lengthOfMonth - date.getDayOfMonth();
        int perDayLimit = limit / lengthOfMonth;

        if (remainingDays == 0) {
            remainingDays = 1;
        }

        return perDayLimit * remainingDays;
    }

    @Override
    public void removeService(Long simCardId, Long serviceId) {
        SIMCard simCard = findById(simCardId);
        Service service = serviceService.findById(serviceId);

        simCard.removeService(service);

        save(simCard);
    }

    @Transactional
    @Override
    public SIMCardView addPackage(AddPackageRequest request, Long id) {
        SIMCard simCard = findById(id);
        Package pack = packageService.findById(request.getPackageId());

        SIMCard saved = addPackage(simCard, pack);

        return simCardMapper.mapToSIMCardView(saved);
    }

    @Transactional
    @Override
    public String addPackage(String username, String code) {
        SIMCard simCard = findByUsername(username);
        Package pack = packageService.findByCode(code);

        addPackage(simCard, pack);

        return String.format("Package - %s added successfully", pack.getName());
    }

    private SIMCard addPackage(SIMCard simCard, Package pack) {
        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, simCard.getId()), null);
        }
        if (simCard.getBalance() < pack.getPrice()) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, simCard.getId(), pack.getPrice()), null);
        }

        TakenPackage takenPackage = new TakenPackage();

        takenPackage.setSimCard(simCard);
        takenPackage.setPack(pack);
        takenPackage.setAmount(pack.getAmount());
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(pack.getDurationType().getValue());
        takenPackage.setExpirationDate(expirationDate);

        simCard.setBalance(simCard.getBalance() - pack.getPrice());
        simCard.addTakenPackage(takenPackage);

        paymentService.create(pack.getPrice(), simCard, PaymentType.PACKAGE);

        return save(simCard);
    }

    @Override
    public SIMCard save(SIMCard simCard) {
        return simCardRepository.save(simCard);
    }
    
    @Override
    public List<SIMCard> findAll() {
        return simCardRepository.findAll();
    }

    @Override
    public List<SIMCard> findAllByServiceType(ServiceType serviceType) {
        return simCardRepository.findAllByServicesType(serviceType);
    }

    @Override
    public SIMCard findById(Long id) {
        return simCardRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public SIMCard findByUsername(String username) {
        return simCardRepository.findByPassportClientUsername(username).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundByUsername, username))
        );
    }

    @Override
    public SIMCard findByNumber(Long number) {
        return simCardRepository.findByNumber(number).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundByNumber, number))
        );
    }

    @Override
    public void deleteById(Long id) {
        SIMCard simCard = findById(id);
        for (Service service : simCard.getServices()) {
            simCard.removeService(service);
        }
        simCardRepository.deleteById(id);
    }

    @Override
    public String getBalance(String username) {
        SIMCard simCard = findByUsername(username);
        return simCard.getBalance() + " UZS";
    }

    @Override
    public String getMinuteLimit(String username) {
        SIMCard simCard = findByUsername(username);
        Integer tariffLimit = simCard.getMinuteLimit();
        Integer packageLimit = simCard.getTakenPackages().stream()
                .filter(TakenPackage::isMinutePackage)
                .map(TakenPackage::getAmount).reduce(0, Integer::sum);
        return "tariff: " + tariffLimit + " minutes, package: " + packageLimit + " minutes";
    }

    @Override
    public String getMBLimit(String username) {
        SIMCard simCard = findByUsername(username);
        Integer tariffLimit = simCard.getMbLimit();
        Integer packageLimit = simCard.getTakenPackages().stream()
                .filter(TakenPackage::isMBPackage)
                .map(TakenPackage::getAmount).reduce(0, Integer::sum);
        return "tariff: " + tariffLimit + " mb, package: " + packageLimit + " mb";
    }

    @Override
    public String getSMSLimit(String username) {
        SIMCard simCard = findByUsername(username);
        Integer tariffLimit = simCard.getMbLimit();
        Integer packageLimit = simCard.getTakenPackages().stream()
                .filter(TakenPackage::isSMSPackage)
                .map(TakenPackage::getAmount).reduce(0, Integer::sum);
        return "tariff: " + tariffLimit + " sms, package: " + packageLimit + " sms";
    }

    @Override
    public String getTariffName(String username) {
        SIMCard simCard = findByUsername(username);
        return simCard.getTariff().getName();
    }

    @Transactional
    @Override
    public void payForTariff(SIMCard simCard) {
        if (simCard.getTariff() != null) {
            if (simCard.getBalance() < simCard.getTariff().getPrice()) {
                simCard.setMinuteLimit(0);
                simCard.setMbLimit(0);
                simCard.setSmsLimit(0);
                simCard.setActive(false);
            } else {
                simCard.setBalance(simCard.getBalance() - simCard.getTariff().getPrice());
                simCard.setMinuteLimit(simCard.getTariff().getPerMonthMinuteLimit());
                simCard.setMbLimit(simCard.getTariff().getPerMonthMBLimit());
                simCard.setSmsLimit(simCard.getTariff().getPerMonthSMSLimit());
                simCard.setActive(true);
                paymentService.create(simCard.getTariff().getPrice(), simCard, PaymentType.TARIFF);
            }
            save(simCard);
        }
    }

    @Transactional
    @Override
    public void payForServices(SIMCard simCard) {
        for (Service service : simCard.getServices()) {
            if (simCard.getBalance() < service.getPrice()) {
                simCard.removeService(service);
                continue;
            }
            simCard.setBalance(simCard.getBalance() - service.getPrice());
            paymentService.create(service.getPrice(), simCard, PaymentType.SERVICE);
        }
        save(simCard);
    }

    @Override
    public void payForSms(String username) {
        SIMCard simCard = findByUsername(username);
        if (!simCard.getActive()) {
            throw new EntityActionVetoException(String.format("SIMCard with id = %d is not active", simCard.getId()), null);
        }
        if (simCard.getSmsLimit() > 0) {
            simCard.setSmsLimit(simCard.getSmsLimit() - 1);
            save(simCard);
            return;
        }
        List<TakenPackage> smsPackages = simCard.getTakenPackages().stream().filter(TakenPackage::isSMSPackage).toList();
        for (TakenPackage smsPackage : smsPackages) {
            if (smsPackage.getAmount() > 0) {
                smsPackage.setAmount(smsPackage.getAmount() - 1);
                save(simCard);
                return;
            }
        }
        if (simCard.getBalance() >= simCard.getTariff().getPerSMSPrice()) {
            simCard.setBalance(simCard.getBalance() - simCard.getTariff().getPerSMSPrice());
            save(simCard);
            return;
        }
        throw new EntityActionVetoException(String.format("SIMCard with id = %d sms is not enough", simCard.getId()), null);
    }

    @Override
    public Integer payForMinute(String username, Integer duration) {
        Integer initDuration = duration;
        SIMCard simCard = findByUsername(username);
        if (!simCard.getActive()) {
            throw new EntityActionVetoException(String.format("SIMCard with id = %d is not active", simCard.getId()), null);
        }
        Integer minuteLimit = simCard.getMinuteLimit();
        if (minuteLimit >= duration) {
            simCard.setMinuteLimit(minuteLimit - duration);
            save(simCard);
            return 0;
        } else {
            duration = duration - minuteLimit;
            simCard.setMinuteLimit(0);
        }
        List<TakenPackage> minutePackages = simCard.getTakenPackages().stream().filter(TakenPackage::isMinutePackage).toList();
        for (TakenPackage minutePackage : minutePackages) {
            Integer amount = minutePackage.getAmount();
            if (amount >= duration) {
                minutePackage.setAmount(amount - duration);
                save(simCard);
                return 0;
            } else {
                duration = duration - amount;
                minutePackage.setAmount(0);
            }
        }
        if (duration <= 0) {
            save(simCard);
            return 0;
        }

        Double balance = simCard.getBalance();
        Double perMinutePrice = simCard.getTariff().getPerMinutePrice();

        if (balance >= (perMinutePrice * duration)) {
            simCard.setBalance(balance - (perMinutePrice * duration));
            save(simCard);
            return 0;
        }

        simCard.setBalance(balance % perMinutePrice);

        duration = duration - (int) (balance / perMinutePrice);

        if (initDuration.equals(duration)) {
            throw new EntityActionVetoException(String.format("SIMCard with id = %d minute is not enough", simCard.getId()), null);
        }

        save(simCard);
        return duration;
    }

    @Override
    public Integer payForMB(String username, Integer bytes) {
        Integer initBytes = bytes;
        SIMCard simCard = findByUsername(username);
        if (!simCard.getActive()) {
            throw new EntityActionVetoException(String.format("SIMCard with id = %d is not active", simCard.getId()), null);
        }
        Integer mbLimit = simCard.getMbLimit();
        if (mbLimit >= bytes) {
            simCard.setMbLimit(mbLimit - bytes);
            save(simCard);
            return 0;
        } else {
            bytes = bytes - mbLimit;
            simCard.setMbLimit(0);
        }
        List<TakenPackage> mbPackages = simCard.getTakenPackages().stream().filter(TakenPackage::isMBPackage).toList();
        for (TakenPackage mbPackage : mbPackages) {
            Integer amount = mbPackage.getAmount();
            if (amount >= bytes) {
                mbPackage.setAmount(amount - bytes);
                save(simCard);
                return 0;
            } else {
                bytes = bytes - amount;
                mbPackage.setAmount(0);
            }
        }
        if (bytes <= 0) {
            save(simCard);
            return 0;
        }

        Double balance = simCard.getBalance();
        Double perMBPrice = simCard.getTariff().getPerMBPrice();

        if (balance >= (perMBPrice * bytes)) {
            simCard.setBalance(balance - (perMBPrice * bytes));
            save(simCard);
            return 0;
        }

        simCard.setBalance(balance % perMBPrice);

        bytes = bytes - (int) (balance / perMBPrice);

        if (initBytes.equals(bytes)) {
            throw new EntityActionVetoException(String.format("SIMCard with id = %d mb is not enough", simCard.getId()), null);
        }

        save(simCard);
        return bytes;
    }
}
