package uz.pdp.networkcompany.dto.view.tariff;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.ClientType;

@Data
@Builder
public class TariffView {
    private Long id;
    private String name;
    private ClientType type;
    private Double price;
    private Double connectionPrice;
    private Integer perMonthMinuteLimit;
    private Integer perMonthMBLimit;
    private Integer perMonthSMSLimit;
    private Double perMinutePrice;
    private Double perMBPrice;
    private Double perSMSPrice;
}
