package uz.pdp.networkcompany.dto.view.pack;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.ClientType;

@Data
@Builder
public class TariffView {
    private Long id;
    private String name;
    private ClientType type;
}
