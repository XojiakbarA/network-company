package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.*;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.*;
import uz.pdp.networkcompany.entity.Package;
import uz.pdp.networkcompany.enums.ServiceType;
import uz.pdp.networkcompany.mapper.SIMCardMapper;
import uz.pdp.networkcompany.repository.SIMCardRepository;
import uz.pdp.networkcompany.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private SIMCardMapper simCardMapper;
    private final String existsByNumber = "SIMCard with number = %s already exists";
    private final String notFoundById = "SIMCard with id = %d not found";
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

    @Override
    public SIMCardView setTariff(SetTariffRequest request, Long id) {
        SIMCard simCard = findById(id);
        Tariff tariff = tariffService.findById(request.getTariffId());

        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, id), null);
        }
        if (!simCard.getPassport().getClient().getType().equals(tariff.getType())) {
            throw new EntityActionVetoException(String.format(tariffAndClientTypesNotMatch, tariff.getType()), null);
        }
        Double priceWithConnection = tariff.getConnectionPrice() + calcPriceForCurrentMonth(tariff.getPrice());
        if (simCard.getBalance() < priceWithConnection) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, id, priceWithConnection), null);
        }

        simCard.setBalance(simCard.getBalance() - priceWithConnection);
        simCard.setMinuteLimit(calcLimitForCurrentMonth(tariff.getPerMonthMinuteLimit()));
        simCard.setMbLimit(calcLimitForCurrentMonth(tariff.getPerMonthMBLimit()));
        simCard.setSmsLimit(calcLimitForCurrentMonth(tariff.getPerMonthSMSLimit()));
        simCard.setActive(true);
        simCard.setTariff(tariff);

        return simCardMapper.mapToSIMCardView(save(simCard));
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

    @Override
    public SIMCardView addBalance(AddAmountRequest request, Long id) {
        SIMCard simCard = findById(id);

        simCard.setBalance(simCard.getBalance() + request.getAmount());

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Override
    public SIMCardView addService(AddServiceRequest request, Long id) {
        SIMCard simCard = findById(id);
        Service service = serviceService.findById(request.getServiceId());

        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, id), null);
        }
        double price = service.getPrice();
        if (service.getType().equals(ServiceType.MONTHLY)) {
            price = calcPriceForCurrentMonth(price);
        }
        if (simCard.getBalance() < price) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, id, price), null);
        }

        simCard.setBalance(simCard.getBalance() - price);
        simCard.addService(service);

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Override
    public void removeService(Long simCardId, Long serviceId) {
        SIMCard simCard = findById(simCardId);
        Service service = serviceService.findById(serviceId);

        simCard.removeService(service);

        save(simCard);
    }

    @Override
    public SIMCardView addPackage(AddPackageRequest request, Long id) {
        SIMCard simCard = findById(id);
        Package pack = packageService.findById(request.getPackageId());

        if (!simCard.getHasClient()) {
            throw new EntityActionVetoException(String.format(noHolderById, id), null);
        }
        if (simCard.getBalance() < pack.getPrice()) {
            throw new EntityActionVetoException(String.format(notEnoughBalanceById, id, pack.getPrice()), null);
        }

        TakenPackage takenPackage = new TakenPackage();

        takenPackage.setSimCard(simCard);
        takenPackage.setPack(pack);
        takenPackage.setAmount(pack.getAmount());
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(pack.getDurationType().getValue());
        takenPackage.setExpirationDate(expirationDate);

        simCard.setBalance(simCard.getBalance() - pack.getPrice());
        simCard.addTakenPackage(takenPackage);

        return simCardMapper.mapToSIMCardView(save(simCard));
    }

    @Override
    public SIMCard save(SIMCard simCard) {
        return simCardRepository.save(simCard);
    }

    @Override
    public SIMCard findById(Long id) {
        return simCardRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
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

    private Double calcPriceForCurrentMonth(Double price) {
        LocalDate date = LocalDate.now();
        int lengthOfMonth = date.getMonth().length(date.isLeapYear());
        int remainingDays = lengthOfMonth - date.getDayOfMonth();
        double perDayPrice = price / lengthOfMonth;

        return perDayPrice * remainingDays;
    }
    private Integer calcLimitForCurrentMonth(Integer limit) {
        LocalDate date = LocalDate.now();
        int lengthOfMonth = date.getMonth().length(date.isLeapYear());
        int remainingDays = lengthOfMonth - date.getDayOfMonth();
        int perDayLimit = limit / lengthOfMonth;

        return perDayLimit * remainingDays;
    }
}
