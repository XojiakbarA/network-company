package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.client.ClientView;
import uz.pdp.networkcompany.dto.view.client.PassportView;
import uz.pdp.networkcompany.dto.view.client.SIMCardView;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Passport;
import uz.pdp.networkcompany.entity.SIMCard;

@Component
public class ClientMapper {
    public ClientView mapToClientView(Client client) {
        if (client == null) return null;
        return ClientView.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .username(client.getUsername())
                .type(client.getType())
                .passport(mapToPassportView(client.getPassport()))
                .simCard(mapToSIMCardView(client.getPassport() != null ? client.getPassport().getSimCard() : null))
                .build();
    }
    private PassportView mapToPassportView(Passport passport) {
        if (passport == null) return null;
        return PassportView.builder()
                .id(passport.getId())
                .number(passport.getNumber())
                .dateOfBirth(passport.getDateOfBirth())
                .dateOfIssue(passport.getDateOfIssue())
                .dateOfExpiration(passport.getDateOfExpiration())
                .build();
    }
    private SIMCardView mapToSIMCardView(SIMCard simCard) {
        if (simCard == null) return null;
        return SIMCardView.builder()
                .id(simCard.getId())
                .number(simCard.getNumber())
                .balance(simCard.getBalance())
                .minuteLimit(simCard.getMinuteLimit())
                .mbLimit(simCard.getMbLimit())
                .smsLimit(simCard.getSmsLimit())
                .active(simCard.getActive())
                .tariffName(simCard.getTariff() != null ? simCard.getTariff().getName() : null)
                .build();
    }
}
