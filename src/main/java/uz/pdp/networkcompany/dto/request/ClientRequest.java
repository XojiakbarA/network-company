package uz.pdp.networkcompany.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.entity.enums.ClientType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@Data
public class ClientRequest {
    @NotNull(message = "firstName must not be null", groups = OnCreate.class)
    @NotBlank(message = "firstName must not be blank")
    @Length(min = 3, max = 50, message = "firstName's length must be in 3 and 50")
    private String firstName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be blank")
    @Length(min = 3, max = 50, message = "lastName's length must be in 3 and 50")
    private String lastName;

    @NotNull(message = "username must not be null", groups = OnCreate.class)
    @NotBlank(message = "username must not be blank")
    @Length(min = 3, max = 50, message = "username's length must be in 3 and 50")
    private String username;

    @NotNull(message = "password must not be null", groups = OnCreate.class)
    @NotBlank(message = "password must not be blank")
    @Length(min = 3, max = 50, message = "password's length must be in 3 and 50")
    private String password;

    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank")
    @IsValidEnum(enumClazz = ClientType.class, message = "type must be any of LEGAL, PHYSICAL")
    private String type;

    @Valid
    private PassportRequest passport;
}
