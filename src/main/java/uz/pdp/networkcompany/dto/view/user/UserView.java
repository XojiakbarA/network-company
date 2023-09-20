package uz.pdp.networkcompany.dto.view.user;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.UserType;

@Data
@Builder
public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private UserType type;
}
