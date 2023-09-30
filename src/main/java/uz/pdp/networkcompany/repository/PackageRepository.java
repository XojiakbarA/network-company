package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Package;

import java.util.Optional;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    boolean existsByUssdCode(String code);
    Optional<Package> findByUssdCode(String code);
}
