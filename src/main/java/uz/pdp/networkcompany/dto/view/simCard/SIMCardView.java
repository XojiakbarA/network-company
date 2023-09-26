package uz.pdp.networkcompany.dto.view.simCard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SIMCardView {
    private Long id;
    private Long number;
    private Double balance;
    private Integer minuteLimit;
    private Integer mbLimit;
    private Integer smsLimit;
    private Boolean active;
    private PassportView passport;
    private TariffView tariff;
}
