package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
