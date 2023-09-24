package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SetLeaderRequest {
    @NotNull(message = "leaderId must not be null")
    @Positive(message = "leaderId must be a positive")
    private Long leaderId;
}
