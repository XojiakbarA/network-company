package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.SIMCardRequest;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.mapper.SIMCardMapper;
import uz.pdp.networkcompany.repository.SIMCardRepository;
import uz.pdp.networkcompany.service.SIMCardService;

@Service
public class SIMCardServiceImpl implements SIMCardService {
    @Autowired
    private SIMCardRepository simCardRepository;
    @Autowired
    private SIMCardMapper simCardMapper;
    private final String existsByNumber = "SIMCard with number = %s already exists";
    private final String notFoundById = "SIMCard with id = %d not found";

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
        if (!simCardRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(notFoundById, id));
        }
        simCardRepository.deleteById(id);
    }
}
