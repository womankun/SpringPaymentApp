package com.example.payment.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;

@Data
public class Payment {
    private final String paymentId;
    private BigDecimal amount;
    private String status;
    private String cardNumber;
    private String cardExpiry;
    private Timestamp createdAt;
    private Timestamp voidedAt;
    private Timestamp refundedAt;
    private Timestamp capturedAt;
    
    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }

}
