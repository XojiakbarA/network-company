package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
