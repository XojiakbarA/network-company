package uz.pdp.networkcompany.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.*;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.enums.ServiceType;

public interface SIMCardService {
    Page<SIMCardView> getAll(Pageable pageable);

    SIMCardView getById(Long id);

    SIMCardView create(SIMCardRequest request);

    SIMCardView update(SIMCardRequest request, Long id);

    SIMCardView setTariff(SetTariffRequest request, Long id);

    String setTariff(String username, String code);

    SIMCardView setPassport(SetPassportRequest request, Long id);

    SIMCardView addBalance(AddAmountRequest request, Long id);

    String addBalance(AddAmountRequest request, String username);

    SIMCardView addService(AddServiceRequest request, Long id);

    String addService(String username, String code);

    void removeService(Long simCardId, Long serviceId);

    SIMCardView addPackage(AddPackageRequest request, Long id);

    String addPackage(String username, String code);

    SIMCard save(SIMCard simCard);

    List<SIMCard> findAll();

    List<SIMCard> findAllByServiceType(ServiceType serviceType);

    SIMCard findById(Long id);

    SIMCard findByUsername(String username);

    SIMCard findByNumber(Long number);

    void deleteById(Long id);

    String getBalance(String username);

    String getMinuteLimit(String username);

    String getMBLimit(String username);

    String getSMSLimit(String username);

    String getTariffName(String username);

    void payForTariff(SIMCard simCard);

    void payForServices(SIMCard simCard);

    void payForSms(String username);

    Integer payForMinute(String username, Integer duration);

    Integer payForMB(String username, Integer bytes);
}
