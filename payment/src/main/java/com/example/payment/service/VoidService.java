package com.example.payment.service;

import com.example.payment.dto.VoidResponse;

public interface VoidService {
    public VoidResponse registerVoid(String paymentId);
}
