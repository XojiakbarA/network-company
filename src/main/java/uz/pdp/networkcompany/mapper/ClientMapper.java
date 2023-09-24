package uz.pdp.networkcompany.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.request.PassportRequest;
import uz.pdp.networkcompany.dto.request.user.ClientRequest;
import uz.pdp.networkcompany.dto.view.client.ClientView;
import uz.pdp.networkcompany.dto.view.client.PassportView;
import uz.pdp.networkcompany.dto.view.client.SIMCardView;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Passport;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.enums.ClientType;

@Component
public class ClientMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public Client mapToClient(ClientRequest request) {
        if (request == null) return null;
        Client client = new Client();

        setAttributes(request, client);

        return client;
    }
    public void mapToClient(ClientRequest request, Client client) {
        setAttributes(request, client);
    }

    private void setAttributes(ClientRequest request, Client client) {
        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            client.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            client.setLastName(request.getLastName());
        }
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            client.setUsername(request.getUsername());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            client.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getType() != null && !request.getType().isBlank()) {
            client.setType(ClientType.valueOf(request.getType().toUpperCase()));
        }
        if (request.getPassport() != null) {
            Passport passport = new Passport();

            if (client.getPassport() != null) {
                passport = client.getPassport();
            }

            setAttributes(request.getPassport(), passport);

            client.setPassport(passport);
        }
    }
    private void setAttributes(PassportRequest request, Passport passport) {
        if (request.getNumber() != null) {
            passport.setNumber(request.getNumber());
        }
        if (request.getDateOfBirth() != null) {
            passport.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getDateOfIssue() != null) {
            passport.setDateOfIssue(request.getDateOfIssue());
        }
        if (request.getDateOfExpiration() != null) {
            passport.setDateOfExpiration(request.getDateOfExpiration());
        }
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
