package com.example.payment.service;

import com.example.payment.dto.RefundResponse;

public interface RefundService {
    public RefundResponse registerRefund(String paymentId);
}
