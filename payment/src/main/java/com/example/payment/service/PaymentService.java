package com.example.payment.service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;

public interface PaymentService {
    public PaymentResponse registerPayment(PaymentRequest paymentRequest);
}