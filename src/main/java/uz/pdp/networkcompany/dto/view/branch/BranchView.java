package uz.pdp.networkcompany.dto.view.branch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchView {
    private Long id;
    private String name;
    private Long phoneNumber;
    private AddressView address;
    private UserView leader;
}
