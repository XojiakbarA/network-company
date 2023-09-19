package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
}
