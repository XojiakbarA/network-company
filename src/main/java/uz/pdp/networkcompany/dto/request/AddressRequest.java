package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.marker.OnCreate;

@Data
public class AddressRequest {
    @NotNull(message = "region must not be null", groups = OnCreate.class)
    @NotBlank(message = "region must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "region's length must be in 3 and 50")
    private String region;

    @NotNull(message = "district must not be null", groups = OnCreate.class)
    @NotBlank(message = "district must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "district's length must be in 3 and 50")
    private String district;

    @NotNull(message = "street must not be null", groups = OnCreate.class)
    @NotBlank(message = "street must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "street's length must be in 3 and 50")
    private String street;

    @NotNull(message = "home must not be null", groups = OnCreate.class)
    @NotBlank(message = "home must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "home's length must be in 3 and 50")
    private String home;
}
