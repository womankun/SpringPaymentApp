package com.example.payment.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.example.payment.validation.ValidExpiry;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Payment {
    private Long paymentId;

    @NotNull(message = "金額は必須です")
    @Min(value = 0, message = "金額は0以上である必要があります")
    private BigDecimal amount;

    private String status;
    
    @NotBlank(message = "カード番号は必須です")
    @Size(min = 16, max = 16, message = "カード番号は16桁で入力してください")
    private String cardNumber;


    @ValidExpiry
    private String cardExpiry;

    private Timestamp createdAt;

    // Getter & Setter
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
