package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.enums.ServiceType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@Data
public class ServiceRequest {
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    @Length(min = 3, max = 50, message = "name's length must be in 3 and 50")
    private String name;

    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = ServiceType.class, message = "type must be any of DAILY, MONTHLY")
    private String type;

    @NotNull(message = "price must not be null", groups = OnCreate.class)
    @Positive(message = "price must be a positive")
    private Double price;
}
