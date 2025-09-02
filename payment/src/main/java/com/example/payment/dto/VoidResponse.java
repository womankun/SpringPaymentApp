package com.example.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoidResponse {
    private final String paymentId;
    private final String status;
    private final String message;
}
