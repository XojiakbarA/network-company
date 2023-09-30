package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.TariffRequest;
import uz.pdp.networkcompany.dto.view.tariff.TariffView;
import uz.pdp.networkcompany.entity.Tariff;

public interface TariffService {
    Page<TariffView> getAll(Pageable pageable);

    TariffView getById(Long id);

    TariffView create(TariffRequest request);

    TariffView update(TariffRequest request, Long id);

    Tariff save(Tariff tariff);

    Tariff findById(Long id);

    Tariff findByUSSDCode(String code);

    boolean existsByCode(String code);

    void deleteById(Long id);
}
