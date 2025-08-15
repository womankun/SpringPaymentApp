package com.example.payment.dto;

import java.util.List;

import com.example.payment.model.Payment;

import lombok.Getter;

@Getter
public class PaymentRecordResponse {
    private final List<Payment> paymentRecords;

    public PaymentRecordResponse(List<Payment> paymentRecords) {
        this.paymentRecords = paymentRecords;
    }
}
