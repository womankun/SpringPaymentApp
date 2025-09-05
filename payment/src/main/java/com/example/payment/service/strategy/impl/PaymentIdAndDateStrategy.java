package com.example.payment.service.strategy.impl;

import org.springframework.stereotype.Component;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.service.PaymentRecordService;
import com.example.payment.service.strategy.PaymentRecordStrategy;

@Component
public class PaymentIdAndDateStrategy implements PaymentRecordStrategy {
  private final PaymentRecordService service;

    public PaymentIdAndDateStrategy(PaymentRecordService service) {
        this.service = service;
    }

    @Override
    public PaymentRecordResponse execute(PaymentRecordRequest request) {
        return service.selectRecordByPaymentIdAndDate(
            request.getPaymentId(), request.getStartDate(), request.getEndDate()
        );
    }
}
