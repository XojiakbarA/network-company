package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.TariffRequest;
import uz.pdp.networkcompany.dto.view.tariff.TariffView;
import uz.pdp.networkcompany.entity.Tariff;
import uz.pdp.networkcompany.enums.ClientType;

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

    public Tariff mapToTariff(TariffRequest request) {
        if (request == null) return null;
        Tariff tariff = new Tariff();

        setAttributes(request, tariff);

        return tariff;
    }
    public void mapToTariff(TariffRequest request, Tariff tariff) {
        setAttributes(request, tariff);
    }
    private void setAttributes(TariffRequest request, Tariff tariff) {
        if (request.getName() != null && !request.getName().isBlank()) {
            tariff.setName(request.getName());
        }
        if (request.getType() != null && !request.getType().isBlank()) {
            tariff.setType(ClientType.valueOf(request.getType().toUpperCase()));
        }
        if (request.getPrice() != null) {
            tariff.setPrice(request.getPrice());
        }
        if (request.getConnectionPrice() != null) {
            tariff.setConnectionPrice(request.getConnectionPrice());
        }
        if (request.getPerMinutePrice() != null) {
            tariff.setPerMinutePrice(request.getPerMinutePrice());
        }
        if (request.getPerMBPrice() != null) {
            tariff.setPerMBPrice(request.getPerMBPrice());
        }
        if (request.getPerSMSPrice() != null) {
            tariff.setPerSMSPrice(request.getPerSMSPrice());
        }
        if (request.getPerMonthMinuteLimit() != null) {
            tariff.setPerMonthMinuteLimit(request.getPerMonthMinuteLimit());
        }
        if (request.getPerMonthMBLimit() != null) {
            tariff.setPerMonthMBLimit(request.getPerMonthMBLimit());
        }
        if (request.getPerMonthSMSLimit() != null) {
            tariff.setPerMonthSMSLimit(request.getPerMonthSMSLimit());
        }
    }
}
