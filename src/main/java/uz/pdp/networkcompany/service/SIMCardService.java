package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.AddAmountRequest;
import uz.pdp.networkcompany.dto.request.SIMCardRequest;
import uz.pdp.networkcompany.dto.request.SetPassportRequest;
import uz.pdp.networkcompany.dto.request.SetTariffRequest;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.SIMCard;

public interface SIMCardService {
    Page<SIMCardView> getAll(Pageable pageable);

    SIMCardView getById(Long id);

    SIMCardView create(SIMCardRequest request);

    SIMCardView update(SIMCardRequest request, Long id);

    SIMCardView setTariff(SetTariffRequest request, Long id);

    SIMCardView setPassport(SetPassportRequest request, Long id);

    SIMCardView addBalance(AddAmountRequest request, Long id);

    SIMCard save(SIMCard simCard);

    SIMCard findById(Long id);

    void deleteById(Long id);
}
