package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsOwnNumber;

@Data
public class SIMCardRequest {
    @NotNull(message = "number must not be null", groups = OnCreate.class)
    @Min(value = 100_000_000_000L, message = "number must be 12 digits")
    @Max(value = 999_999_999_999L, message = "number must be 12 digits")
    @IsOwnNumber(startCode = "99895")
    private Long number;
}
