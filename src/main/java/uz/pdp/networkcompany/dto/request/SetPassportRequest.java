package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SetPassportRequest {
    @NotNull(message = "passportId must not be null")
    @Positive(message = "passportId must be a positive")
    private Long passportId;
}
