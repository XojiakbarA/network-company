package uz.pdp.networkcompany.dto.view.payment;

import lombok.Builder;
import lombok.Data;
import uz.pdp.networkcompany.enums.PaymentType;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentView {
    private Long id;
    private Double amount;
    private String clientUsername;
    private Long simCardNumber;
    private PaymentType type;
    private LocalDateTime createdAt;
}
