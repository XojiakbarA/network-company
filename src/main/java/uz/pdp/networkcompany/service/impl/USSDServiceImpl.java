package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.repository.USSDRepository;
import uz.pdp.networkcompany.service.USSDService;

@Service
public class USSDServiceImpl implements USSDService {
    @Autowired
    private USSDRepository ussdRepository;

    @Override
    public boolean existsByCode(String code) {
        return ussdRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, Long id) {
        return ussdRepository.existsByCodeAndIdNot(code, id);
    }
}
