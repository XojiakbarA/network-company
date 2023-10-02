package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.enums.ServiceType;

import java.util.List;
import java.util.Optional;

@Repository
public interface SIMCardRepository extends JpaRepository<SIMCard, Long> {
    boolean existsByNumber(Long number);
    boolean existsByNumberAndIdNot(Long number, Long id);
    Optional<SIMCard> findByPassportClientUsername(String username);
    Optional<SIMCard> findByNumber(Long number);
    List<SIMCard> findAllByServicesType(ServiceType serviceType);
}
