package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
