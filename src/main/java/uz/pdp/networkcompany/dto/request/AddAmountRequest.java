package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddAmountRequest {
    @NotNull(message = "amount must not be null")
    @Positive(message = "amout must be a positive")
    private Double amount;
}
