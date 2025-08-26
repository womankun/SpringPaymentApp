package com.example.payment.service;

import com.example.payment.dto.PaymentRecordResponse;

public interface PaymentRecordService {
    public PaymentRecordResponse selectAllRecords();
    public PaymentRecordResponse selectRecordByPaymentId(String paymentId);
}
