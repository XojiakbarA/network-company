package uz.pdp.networkcompany.dto.view.pack;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.DurationType;
import uz.pdp.networkcompany.enums.PackageType;

import java.util.Set;

@Data
@Builder
public class PackageView {
    private Long id;
    private String name;
    private Double price;
    private Integer amount;
    private PackageType type;
    private DurationType durationType;
    private Boolean leftover;
    private Set<TariffView> tariffs;
}
