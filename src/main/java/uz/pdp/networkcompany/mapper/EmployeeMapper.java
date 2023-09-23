package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.employee.EmployeeView;
import uz.pdp.networkcompany.entity.Employee;

@Component
public class EmployeeMapper {
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
}
