package uz.pdp.networkcompany.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.marker.OnCreate;

@Data
public class BranchRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be blank")
    @Length(min = 3, max = 50, message = "name's length must be in 3 and 50")
    private String name;

    @NotNull(message = "phoneNumber must not be null", groups = OnCreate.class)
    @Min(value = 100_000_000_000L, message = "phoneNumber must be 12 digits")
    @Max(value = 999_999_999_999L, message = "phoneNumber must be 12 digits")
    private Long phoneNumber;

    @Valid
    @NotNull(message = "address must not be null", groups = OnCreate.class)
    private AddressRequest address;
}
