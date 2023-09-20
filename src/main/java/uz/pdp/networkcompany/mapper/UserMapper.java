package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.user.UserView;
import uz.pdp.networkcompany.entity.User;

@Component
public class UserMapper {
    public UserView mapToUserView(User user) {
        if (user == null) return null;
        return UserView.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .type(user.getType())
                .build();
    }
}
