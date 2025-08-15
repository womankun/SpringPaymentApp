package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createPayment(@RequestBody @Valid Payment payment) {
        paymentService.registerPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Payment registered");
    }
}
