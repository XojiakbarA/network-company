package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.user.EmployeeRequest;
import uz.pdp.networkcompany.dto.view.employee.EmployeeView;
import uz.pdp.networkcompany.entity.Employee;

public interface EmployeeService {
    Page<EmployeeView> getAll(Pageable pageable);

    EmployeeView getById(Long id);

    EmployeeView create(EmployeeRequest request);

    EmployeeView update(EmployeeRequest request, Long id);

    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);
}
