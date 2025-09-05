package com.example.payment.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.mapper.PaymentRecordMapper;
import com.example.payment.service.PaymentRecordService;

@Service
public class PaymentRecordServiceImpl implements PaymentRecordService{
    private final PaymentRecordMapper PaymentRecordMapper;

    public PaymentRecordServiceImpl(PaymentRecordMapper PaymentRecordMapper) {
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

    public PaymentRecordResponse selectRecordByDate(LocalDate startDate, LocalDate endDate) {
        PaymentRecordResponse response = new PaymentRecordResponse(PaymentRecordMapper.selectRecordByDate(startDate, endDate));
        return response;
    }

    public PaymentRecordResponse selectRecordByPaymentIdAndDate(String paymentId, LocalDate startDate, LocalDate endDate) {
        PaymentRecordResponse response = new PaymentRecordResponse(PaymentRecordMapper.selectRecordByPaymentIdAndDate(paymentId, startDate, endDate));
        return response;
    }
}
