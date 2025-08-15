package com.example.payment.service;

import com.example.payment.mapper.PaymentRecordMapper;
import com.example.payment.model.Payment;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PaymentRecordService {

    private final PaymentRecordMapper PaymentRecordMapper;

    public PaymentRecordService(PaymentRecordMapper PaymentRecordMapper) {
        this.PaymentRecordMapper = PaymentRecordMapper;
    }

    public List<Payment> selectAllRecords() {
        return PaymentRecordMapper.selectAllRecords();
    }
}
