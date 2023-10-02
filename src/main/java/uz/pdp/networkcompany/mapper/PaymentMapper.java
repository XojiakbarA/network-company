package uz.pdp.networkcompany.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.networkcompany.dto.view.payment.PaymentView;
import uz.pdp.networkcompany.entity.Payment;

@Component
public class PaymentMapper {
    public PaymentView mapToPaymentView(Payment payment) {
        if (payment == null) return null;
        return PaymentView.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .type(payment.getType())
                .clientUsername(payment.getPayer().getPassport().getClient().getUsername())
                .simCardNumber(payment.getPayer().getNumber())
                .createdAt(payment.getCreatedAt())
                .build();

    }
}
