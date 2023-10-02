package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Minute;

@Repository
public interface MinuteRepository extends JpaRepository<Minute, Long> {
}
