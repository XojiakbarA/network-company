package uz.pdp.networkcompany.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.marker.OnCreate;

@Data
public class UserRequest {
    @NotNull(message = "firstName must not be null", groups = OnCreate.class)
    @NotBlank(message = "firstName must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "firstName's length must be in 3 and 50")
    private String firstName;

    @NotNull(message = "lastName must not be null", groups = OnCreate.class)
    @NotBlank(message = "lastName must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "lastName's length must be in 3 and 50")
    private String lastName;

    @NotNull(message = "username must not be null", groups = OnCreate.class)
    @NotBlank(message = "username must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "username's length must be in 3 and 50")
    private String username;

    @NotNull(message = "password must not be null", groups = OnCreate.class)
    @NotBlank(message = "password must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "password's length must be in 3 and 50")
    private String password;
}
