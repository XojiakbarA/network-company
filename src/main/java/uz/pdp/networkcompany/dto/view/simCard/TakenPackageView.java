package uz.pdp.networkcompany.dto.view.simCard;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.PackageType;

import java.time.LocalDateTime;

@Data
@Builder
public class TakenPackageView {
    private Long id;
    private PackageType type;
    private LocalDateTime expirationDate;
    private Integer amount;
}
