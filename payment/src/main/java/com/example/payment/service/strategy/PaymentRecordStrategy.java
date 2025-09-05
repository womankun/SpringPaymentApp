package com.example.payment.service.strategy;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.dto.PaymentRecordResponse;

public interface PaymentRecordStrategy {
  PaymentRecordResponse execute(PaymentRecordRequest request);
}
