package com.example.payment.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.service.PaymentRecordService;
import com.example.payment.service.strategy.PaymentRecordStrategy;

@Component
public class PaymentIdOnlyStrategy implements PaymentRecordStrategy {
    private final PaymentRecordService service;

    public PaymentIdOnlyStrategy(PaymentRecordService service) {
        this.service = service;
    }

    @Override
    public PaymentRecordResponse execute(PaymentRecordRequest request) {
        return service.selectRecordByPaymentId(request.getPaymentId());
    }
}
