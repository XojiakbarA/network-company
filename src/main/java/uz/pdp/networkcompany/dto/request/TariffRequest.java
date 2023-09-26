package uz.pdp.networkcompany.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import uz.pdp.networkcompany.enums.ClientType;
import uz.pdp.networkcompany.marker.OnCreate;
import uz.pdp.networkcompany.validator.IsValidEnum;

@Data
public class TariffRequest {
    @NotNull(message = "name must not be null", groups = OnCreate.class)
    @NotBlank(message = "name must not be blank", groups = OnCreate.class)
    @Length(min = 3, max = 50, message = "name's length must be in 3 and 50")
    private String name;

    @NotNull(message = "type must not be null", groups = OnCreate.class)
    @NotBlank(message = "type must not be blank", groups = OnCreate.class)
    @IsValidEnum(enumClazz = ClientType.class, message = "type must be any of LEGAL, PHYSICAL")
    private String type;

    @NotNull(message = "price must not be null", groups = OnCreate.class)
    @Positive(message = "price must be a positive")
    private Double price;

    @NotNull(message = "connectionPrice must not be null", groups = OnCreate.class)
    @Positive(message = "connectionPrice must be a positive")
    private Double connectionPrice;

    @NotNull(message = "perMonthMinuteLimit must not be null", groups = OnCreate.class)
    @Positive(message = "perMonthMinuteLimit must be a positive")
    private Integer perMonthMinuteLimit;

    @NotNull(message = "perMonthMBLimit must not be null", groups = OnCreate.class)
    @Positive(message = "perMonthMBLimit must be a positive")
    private Integer perMonthMBLimit;

    @NotNull(message = "perMonthSMSLimit must not be null", groups = OnCreate.class)
    @Positive(message = "perMonthSMSLimit must be a positive")
    private Integer perMonthSMSLimit;

    @NotNull(message = "perMinutePrice must not be null", groups = OnCreate.class)
    @Positive(message = "perMinutePrice must be a positive")
    private Double perMinutePrice;

    @NotNull(message = "perMBPrice must not be null", groups = OnCreate.class)
    @Positive(message = "perMBPrice must be a positive")
    private Double perMBPrice;

    @NotNull(message = "perSMSPrice must not be null", groups = OnCreate.class)
    @Positive(message = "perSMSPrice must be a positive")
    private Double perSMSPrice;
}
