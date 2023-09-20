package uz.pdp.networkcompany.dto.view.client;

import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

@Data
public class TariffView {
    private Long id;
    private String name;
    private ClientType type;
}
