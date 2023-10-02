package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.MB;

@Repository
public interface MBRepository extends JpaRepository<MB, Long> {
}
