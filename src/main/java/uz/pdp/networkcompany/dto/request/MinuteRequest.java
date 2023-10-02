package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsOwnNumber;

@Data
public class MinuteRequest {
    @NotNull(message = "duration must not be null", groups = OnCreate.class)
    @Positive(message = "duration must be a positive")
    private Integer duration;

    @NotNull(message = "receiverNumber must not be null")
    @Min(value = 100_000_000_000L, message = "receiverNumber must be 12 digits")
    @Max(value = 999_999_999_999L, message = "receiverNumber must be 12 digits")
    @IsOwnNumber(startCode = "99895")
    private Long receiverNumber;
}
