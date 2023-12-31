package uz.pdp.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface UserRepository<T extends UserDetails, ID extends Serializable> extends JpaRepository<T, ID> {
    Optional<T> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, Long id);
}
