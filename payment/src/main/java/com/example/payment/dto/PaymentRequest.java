package com.example.payment.dto;

import java.math.BigDecimal;

import com.example.payment.validation.ValidExpiry;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PaymentRequest {

    @NotNull(message = "金額は必須です")
    @Min(value = 0, message = "金額は0以上である必要があります")
    private BigDecimal amount;
    
    @NotBlank(message = "カード番号は必須です")
    @Size(min = 16, max = 16, message = "カード番号は16桁で入力してください")
    private String cardNumber;

    @ValidExpiry
    private String cardExpiry;

}
