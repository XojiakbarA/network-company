package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddPackageRequest {
    @NotNull(message = "packageId must not be null")
    @Positive(message = "packageId must be a positive")
    private Long packageId;
}
