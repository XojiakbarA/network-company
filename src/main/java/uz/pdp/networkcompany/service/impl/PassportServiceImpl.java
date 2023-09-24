package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.entity.Passport;
import uz.pdp.networkcompany.repository.PassportRepository;
import uz.pdp.networkcompany.service.PassportService;

@Service
public class PassportServiceImpl implements PassportService {
    @Autowired
    private PassportRepository passportRepository;
    private final String notFoundById = "Passport with id = %d not found";

    @Override
    public Passport findById(Long id) {
        return passportRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }
}
