package uz.pdp.networkcompany.dto.view.simCard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassportView {
    private Long id;
    private String number;
    private ClientView client;
}
