package uz.pdp.networkcompany.dto.request.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.networkcompany.dto.request.PassportRequest;
import uz.pdp.networkcompany.enums.ClientType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientRequest extends UserRequest {
    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = ClientType.class, message = "type must be any of LEGAL, PHYSICAL")
    private String type;

    @Valid
    @NotNull(message = "passport must not be null", groups = OnCreate.class)
    private PassportRequest passport;
}
