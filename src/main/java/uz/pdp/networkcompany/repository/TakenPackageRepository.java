package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.TakenPackage;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TakenPackageRepository extends JpaRepository<TakenPackage, Long> {
    List<TakenPackage> findAllByExpirationDateBefore(LocalDateTime dateTime);
}
