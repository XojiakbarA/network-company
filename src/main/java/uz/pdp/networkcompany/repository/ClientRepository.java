package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
