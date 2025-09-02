package com.example.payment.service;

import com.example.payment.dto.CaptureResponse;

public interface CaptureService {
    public CaptureResponse registerCapture(String paymentId);
}
