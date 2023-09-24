package uz.pdp.networkcompany.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.networkcompany.enums.EmployeeType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeRequest extends UserRequest {
    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = EmployeeType.class, message = "type must be any of DIRECTOR, BRANCH_MANAGER, TARIFF_MANAGER, SIM_CARD_MANAGER, BRANCH_LEADER, WORKER")
    private String type;
}
