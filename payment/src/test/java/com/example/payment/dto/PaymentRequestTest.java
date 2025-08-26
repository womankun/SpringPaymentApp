package com.example.payment.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void validPaymentRequest_shouldPassValidation() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        setField(paymentRequest, "amount", BigDecimal.valueOf(1000));
        setField(paymentRequest, "cardNumber", "1234567812345678");
        setField(paymentRequest, "cardExpiry", "0826");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertThat(violations).isEmpty();
    }

    @Test
    void invalidCardNumber_shouldFailValidation() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        setField(paymentRequest, "amount", BigDecimal.valueOf(1000));
        setField(paymentRequest, "cardNumber", "1234"); // 不正な桁数
        setField(paymentRequest, "cardExpiry", "0826");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("cardNumber"));
    }

    @Test
    void negativeAmount_shouldFailValidation() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        setField(paymentRequest, "amount", BigDecimal.valueOf(-100));
        setField(paymentRequest, "cardNumber", "1234567812345678");
        setField(paymentRequest, "cardExpiry", "0826");

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("amount"));
    }

    @Test
    void invalidExpiry_shouldFailValidation() throws Exception {
        PaymentRequest paymentRequest = new PaymentRequest();
        setField(paymentRequest, "amount", BigDecimal.valueOf(1000));
        setField(paymentRequest, "cardNumber", "1234567812345678");
        setField(paymentRequest, "cardExpiry", "0120"); // 過去の年月

        Set<ConstraintViolation<PaymentRequest>> violations = validator.validate(paymentRequest);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("cardExpiry"));
    }
}
