package uz.pdp.networkcompany.dto.view.client;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

@Data
@Builder
public class ClientView {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private ClientType type;
    private PassportView passport;
    private SIMCardView simCard;
}
