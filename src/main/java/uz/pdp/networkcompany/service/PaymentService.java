package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.view.payment.PaymentView;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.enums.PaymentType;

public interface PaymentService {
    Page<PaymentView> getAll(Pageable pageable);
    void create(Double amount, SIMCard payer, PaymentType type);
}
