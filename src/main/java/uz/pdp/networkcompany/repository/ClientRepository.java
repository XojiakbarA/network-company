package uz.pdp.networkcompany.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Client;

@Repository
public interface ClientRepository extends UserRepository<Client, Long> {

}
