package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.entity.TakenPackage;
import uz.pdp.networkcompany.repository.TakenPackageRepository;
import uz.pdp.networkcompany.service.TakenPackageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TakenPackageServiceImpl implements TakenPackageService {
    @Autowired
    private TakenPackageRepository takenPackageRepository;

    @Override
    public List<TakenPackage> findAllByExpirationDateBefore(LocalDateTime dateTime) {
        return takenPackageRepository.findAllByExpirationDateBefore(dateTime);
    }

    @Override
    public void deleteById(Long id) {
        takenPackageRepository.deleteById(id);
    }
}
