package uz.pdp.networkcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.networkcompany.dto.response.Response;
import uz.pdp.networkcompany.dto.view.payment.PaymentView;
import uz.pdp.networkcompany.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PreAuthorize("hasAnyAuthority(" +
            "T(uz.pdp.networkcompany.enums.AuthorityType).CRUD_ALL," +
            "T(uz.pdp.networkcompany.enums.AuthorityType).GET_PAYMENTS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<PaymentView> payments = paymentService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), payments);
    }
}
