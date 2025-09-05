package com.example.payment.controller;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.service.strategy.PaymentRecordStrategy;
import com.example.payment.service.strategy.PaymentRecordStrategyFactory;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-records")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class PaymentRecordController {

    private final PaymentRecordStrategyFactory strategyFactory;

    public PaymentRecordController(PaymentRecordStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @PostMapping
    public ResponseEntity<PaymentRecordResponse> selectRecord(@RequestBody @Valid PaymentRecordRequest request) {
        PaymentRecordStrategy strategy = strategyFactory.resolve(request);
        return ResponseEntity.ok(strategy.execute(request));
    }
}