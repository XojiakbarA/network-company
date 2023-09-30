package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTariffRequest {
    @NotNull(message = "tariffId must not be null")
    @Positive(message = "tariffId must be a positive")
    private Long tariffId;
}
