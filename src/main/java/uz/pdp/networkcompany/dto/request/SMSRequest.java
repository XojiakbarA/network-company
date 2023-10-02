package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsOwnNumber;

@Data
public class SMSRequest {
    @NotNull(message = "content must not be null", groups = OnCreate.class)
    @NotBlank(message = "content must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 100, message = "content's length must be in 3 and 100")
    private String content;

    @NotNull(message = "receiverNumber must not be null")
    @Min(value = 100_000_000_000L, message = "receiverNumber must be 12 digits")
    @Max(value = 999_999_999_999L, message = "receiverNumber must be 12 digits")
    @IsOwnNumber(startCode = "99895")
    private Long receiverNumber;
}
