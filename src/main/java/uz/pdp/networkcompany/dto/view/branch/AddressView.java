package uz.pdp.networkcompany.dto.view.branch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressView {
    private Long id;
    private String region;
    private String district;
    private String street;
    private String home;
}
