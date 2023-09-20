package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.simCard.ClientView;
import uz.pdp.networkcompany.dto.view.simCard.PassportView;
import uz.pdp.networkcompany.dto.view.simCard.SIMCardView;
import uz.pdp.networkcompany.dto.view.simCard.TariffView;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Passport;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.entity.Tariff;

@Component
public class SIMCardMapper {
    public SIMCardView mapToSIMCardView(SIMCard simCard) {
        if (simCard == null) return null;
        return SIMCardView.builder()
                .id(simCard.getId())
                .number(simCard.getNumber())
                .balance(simCard.getBalance())
                .minuteLimit(simCard.getMinuteLimit())
                .mbLimit(simCard.getMbLimit())
                .smsLimit(simCard.getSmsLimit())
                .active(simCard.getActive())
                .passport(mapToPassportView(simCard.getPassport()))
                .tariff(mapToTariffView(simCard.getTariff()))
                .build();
    }
    private PassportView mapToPassportView(Passport passport) {
        if (passport == null) return null;
        return PassportView.builder()
                .id(passport.getId())
                .number(passport.getNumber())
                .client(mapToClientView(passport.getClient()))
                .build();
    }
    private ClientView mapToClientView(Client client) {
        if (client == null) return null;
        return ClientView.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .type(client.getType())
                .build();
    }
    private TariffView mapToTariffView(Tariff tariff) {
        if (tariff == null) return null;
        return TariffView.builder()
                .id(tariff.getId())
                .name(tariff.getName())
                .type(tariff.getType())
                .build();
    }
}
