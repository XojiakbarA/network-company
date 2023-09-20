package uz.pdp.networkcompany.dto.view.simCard;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

@Data
@Builder
public class ClientView {
    private Long id;
    private String firstName;
    private String lastName;
    private ClientType type;
}
