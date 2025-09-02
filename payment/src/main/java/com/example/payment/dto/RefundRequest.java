package com.example.payment.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class RefundRequest {
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "paymentIdはUUID形式で指定してください"
    )
    private String paymentId;
}
