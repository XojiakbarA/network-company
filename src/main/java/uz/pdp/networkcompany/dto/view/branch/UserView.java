package uz.pdp.networkcompany.dto.view.branch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserView {
    private Long id;
    private String firstName;
    private String lastName;
}
