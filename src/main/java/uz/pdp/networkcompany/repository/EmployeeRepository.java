package uz.pdp.networkcompany.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.enums.EmployeeType;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends UserRepository<Employee, Long> {
    Optional<Employee> findByIdAndType(Long id, EmployeeType type);
}
