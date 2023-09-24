package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.SIMCardRequest;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.SIMCard;

public interface SIMCardService {
    Page<SIMCardView> getAll(Pageable pageable);

    SIMCardView getById(Long id);

    SIMCardView create(SIMCardRequest request);

    SIMCardView update(SIMCardRequest request, Long id);

    SIMCard save(SIMCard simCard);

    SIMCard findById(Long id);

    void deleteById(Long id);
}
