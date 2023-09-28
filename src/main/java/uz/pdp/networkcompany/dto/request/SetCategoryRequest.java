package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SetCategoryRequest {
    @NotNull(message = "categoryId must not be null")
    @Positive(message = "categoryId must be a positive")
    private Long categoryId;
}
