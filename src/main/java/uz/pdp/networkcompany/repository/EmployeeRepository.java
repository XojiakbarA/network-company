package uz.pdp.networkcompany.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.networkcompany.entity.Employee;

@Repository
public interface EmployeeRepository extends UserRepository<Employee, Long> {
}
