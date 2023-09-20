package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.tariff.TariffView;
import uz.pdp.networkcompany.entity.Tariff;

@Component
public class TariffMapper {
    public TariffView mapToTariffView(Tariff tariff) {
        if (tariff == null) return null;
        return TariffView.builder()
                .id(tariff.getId())
                .name(tariff.getName())
                .type(tariff.getType())
                .price(tariff.getPrice())
                .connectionPrice(tariff.getConnectionPrice())
                .perMonthMinuteLimit(tariff.getPerMonthMinuteLimit())
                .perMonthMBLimit(tariff.getPerMonthMBLimit())
                .perMonthSMSLimit(tariff.getPerMonthSMSLimit())
                .perMinutePrice(tariff.getPerMinutePrice())
                .perMBPrice(tariff.getPerMBPrice())
                .perSMSPrice(tariff.getPerSMSPrice())
                .build();
    }
}
