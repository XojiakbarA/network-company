package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.passport.ClientView;
import uz.pdp.networkcompany.dto.view.passport.PassportView;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.entity.Passport;

@Component
public class PassportMapper {
    public PassportView mapToPassportView(Passport passport) {
        if (passport == null) return null;
        return PassportView.builder()
                .id(passport.getId())
                .number(passport.getNumber())
                .dateOfBirth(passport.getDateOfBirth())
                .dateOfIssue(passport.getDateOfIssue())
                .dateOfExpiration(passport.getDateOfExpiration())
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
}
