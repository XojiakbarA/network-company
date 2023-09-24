package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.TariffRequest;
import uz.pdp.networkcompany.dto.view.tariff.TariffView;
import uz.pdp.networkcompany.entity.Tariff;
import uz.pdp.networkcompany.mapper.TariffMapper;
import uz.pdp.networkcompany.repository.TariffRepository;
import uz.pdp.networkcompany.service.TariffService;

@Service
public class TariffServiceImpl implements TariffService {
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    private TariffMapper tariffMapper;
    private final String existsByName = "Tariff with name = %s already exists";
    private final String notFoundById = "Tariff with id = %d not found";

    @Override
    public Page<TariffView> getAll(Pageable pageable) {
        return tariffRepository.findAll(pageable).map(t -> tariffMapper.mapToTariffView(t));
    }

    @Override
    public TariffView getById(Long id) {
        return tariffMapper.mapToTariffView(findById(id));
    }

    @Override
    public TariffView create(TariffRequest request) {
        if (tariffRepository.existsByName(request.getName())) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Tariff tariff = tariffMapper.mapToTariff(request);

        return tariffMapper.mapToTariffView(save(tariff));
    }

    @Override
    public TariffView update(TariffRequest request, Long id) {
        if (tariffRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new EntityExistsException(String.format(existsByName, request.getName()));
        }
        Tariff tariff = findById(id);

        tariffMapper.mapToTariff(request, tariff);

        return tariffMapper.mapToTariffView(save(tariff));
    }

    @Override
    public Tariff save(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    @Override
    public Tariff findById(Long id) {
        return tariffRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!tariffRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(notFoundById, id));
        }
        tariffRepository.deleteById(id);
    }
}
