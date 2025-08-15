package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 登録API
    @PostMapping
    public void createPayment(@RequestBody Payment payment) {
        paymentService.registerPayment(payment);
    }
}
