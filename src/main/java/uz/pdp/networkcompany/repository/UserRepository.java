package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
