package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.SMS;

@Repository
public interface SMSRepository extends JpaRepository<SMS, Long> {
}
