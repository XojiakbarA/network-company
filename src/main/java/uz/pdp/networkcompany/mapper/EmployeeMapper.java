package uz.pdp.networkcompany.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.user.EmployeeRequest;
import uz.pdp.networkcompany.dto.view.employee.EmployeeView;
import uz.pdp.networkcompany.entity.Employee;
import uz.pdp.networkcompany.enums.EmployeeType;

@Component
public class EmployeeMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeView mapToEmployeeView(Employee employee) {
        if (employee == null) return null;
        return EmployeeView.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .username(employee.getUsername())
                .type(employee.getType())
                .build();
    }
    public Employee mapToEmployee(EmployeeRequest request) {
        if (request == null) return null;
        Employee employee = new Employee();

        setAttributes(request, employee);

        return employee;
    }
    public void mapToEmployee(EmployeeRequest request, Employee employee) {
        setAttributes(request, employee);
    }

    private void setAttributes(EmployeeRequest request, Employee employee) {
        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            employee.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            employee.setLastName(request.getLastName());
        }
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            employee.setUsername(request.getUsername());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            employee.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getType() != null && !request.getType().isBlank()) {
            employee.setType(EmployeeType.valueOf(request.getType().toUpperCase()));
        }
    }
}
