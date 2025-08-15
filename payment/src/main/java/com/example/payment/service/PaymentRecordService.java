package com.example.payment.service;

import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.mapper.PaymentRecordMapper;

import org.springframework.stereotype.Service;

@Service
public class PaymentRecordService {

    private final PaymentRecordMapper PaymentRecordMapper;

    public PaymentRecordService(PaymentRecordMapper PaymentRecordMapper) {
        this.PaymentRecordMapper = PaymentRecordMapper;
    }

    public PaymentRecordResponse selectAllRecords() {
        PaymentRecordResponse response = new PaymentRecordResponse(PaymentRecordMapper.selectAllRecords());
        return response;
    }

    public PaymentRecordResponse selectRecordByPaymentId(String paymentId) {
        PaymentRecordResponse response = new PaymentRecordResponse(PaymentRecordMapper.selectRecordByPaymentId(paymentId));
        return response;
    }
}
