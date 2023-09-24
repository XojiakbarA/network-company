package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.SIMCard;

@Repository
public interface SIMCardRepository extends JpaRepository<SIMCard, Long> {
    boolean existsByNumber(Long number);
    boolean existsByNumberAndIdNot(Long number, Long id);
}
