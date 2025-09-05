package com.example.payment.service;

import java.time.LocalDate;

import com.example.payment.dto.PaymentRecordResponse;

public interface PaymentRecordService {
    public PaymentRecordResponse selectAllRecords();
    public PaymentRecordResponse selectRecordByPaymentId(String paymentId);
    public PaymentRecordResponse selectRecordByDate(LocalDate startDate, LocalDate endDate);
    public PaymentRecordResponse selectRecordByPaymentIdAndDate(String paymentId, LocalDate startDate, LocalDate endDate);
}
