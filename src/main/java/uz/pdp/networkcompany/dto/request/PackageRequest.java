package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.enums.DurationType;
import uz.pdp.networkcompany.enums.PackageType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@Data
public class PackageRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "name's length must be in 3 and 50")
    private String name;

    @NotNull(message = "price must not be null", groups = OnCreate.class)
    @Positive(message = "price must be a positive")
    private Double price;

    @NotNull(message = "amount must not be null", groups = OnCreate.class)
    @Positive(message = "amount must be a positive")
    private Integer amount;

    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = PackageType.class, message = "type must be any of MINUTE, MB, SMS")
    private String type;

    @NotNull(message = "durationType must not be null", groups = OnCreate.class)
    @NotBlank(message = "durationType must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = DurationType.class, message = "type must be any of DAY, THREE_DAY, WEEK, MONTH")
    private String durationType;

    private Boolean leftover;
}
