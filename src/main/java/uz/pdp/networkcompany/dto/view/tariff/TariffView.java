package uz.pdp.networkcompany.dto.view.tariff;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

@Data
@Builder
public class TariffView {
    private Long id;
    private String name;
    private ClientType type;
    private Float price;
    private Float connectionPrice;
    private Integer perMonthMinuteLimit;
    private Integer perMonthMBLimit;
    private Integer perMonthSMSLimit;
    private Float perMinutePrice;
    private Float perMBPrice;
    private Float perSMSPrice;
}
