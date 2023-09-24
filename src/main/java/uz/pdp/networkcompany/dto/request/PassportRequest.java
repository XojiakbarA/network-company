package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsFuture;
import uz.pdp.networkcompany.validator.IsPast;

import java.util.Date;

@Data
public class PassportRequest {
    @NotNull(message = "number must not be null", groups = OnCreate.class)
    @NotBlank(message = "number must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "number's length must be in 3 and 50")
    private String number;

    @NotNull(message = "dateOfBirth must not be null", groups = OnCreate.class)
    @IsPast(message = "dateOfBirth must be past", groups = OnCreate.class)
    private Date dateOfBirth;

    @NotNull(message = "dateOfIssue must not be null", groups = OnCreate.class)
    @IsPast(message = "dateOfIssue must be past", groups = OnCreate.class)
    private Date dateOfIssue;

    @NotNull(message = "dateOfExpiration must not be null", groups = OnCreate.class)
    @IsFuture(message = "dateOfExpiration must be future")
    private Date dateOfExpiration;
}
