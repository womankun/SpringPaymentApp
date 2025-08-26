package com.example.payment.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Payment {
    private final String paymentId;
    @Setter
    private BigDecimal amount;
    @Setter
    private String status;
    @Setter
    private String cardNumber;
    @Setter
    private String cardExpiry;
    private Timestamp createdAt;
    
    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
    }

}
