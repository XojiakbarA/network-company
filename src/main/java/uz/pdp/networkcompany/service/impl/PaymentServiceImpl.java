package uz.pdp.networkcompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.view.payment.PaymentView;
import uz.pdp.networkcompany.entity.Payment;
import uz.pdp.networkcompany.entity.SIMCard;
import uz.pdp.networkcompany.enums.PaymentType;
import uz.pdp.networkcompany.mapper.PaymentMapper;
import uz.pdp.networkcompany.repository.PaymentRepository;
import uz.pdp.networkcompany.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Page<PaymentView> getAll(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(p -> paymentMapper.mapToPaymentView(p));
    }

    @Override
    public void create(Double amount, SIMCard payer, PaymentType type) {
        Payment payment = new Payment();

        payment.setAmount(amount);
        payment.setPayer(payer);
        payment.setType(type);

        paymentRepository.save(payment);
    }
}
