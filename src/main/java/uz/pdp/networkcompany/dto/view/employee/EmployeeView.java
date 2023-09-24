package uz.pdp.networkcompany.dto.view.employee;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.EmployeeType;

@Data
@Builder
public class EmployeeView {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private EmployeeType type;
}
