package uz.pdp.networkcompany.dto.view.simCard;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.entity.enums.ClientType;

@Data
@Builder
public class TariffView {
    private Long id;
    private String name;
    private ClientType type;
}