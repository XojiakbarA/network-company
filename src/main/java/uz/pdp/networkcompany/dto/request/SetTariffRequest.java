package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SetTariffRequest {
    @NotNull(message = "tariffId must not be null")
    @Positive(message = "tariffId must be a positive")
    private Long tariffId;
}
