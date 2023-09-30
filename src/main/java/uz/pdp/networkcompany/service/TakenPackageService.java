package uz.pdp.networkcompany.service;

import uz.pdp.networkcompany.entity.TakenPackage;

import java.time.LocalDateTime;
import java.util.List;

public interface TakenPackageService {
    List<TakenPackage> findAllByExpirationDateBefore(LocalDateTime dateTime);

    void deleteById(Long id);
}
