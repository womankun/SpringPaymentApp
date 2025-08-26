package com.example.payment.model;

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

public class PaymentTest {

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
    void validPayment_shouldPassValidation() throws Exception {
        Payment payment = new Payment();
        setField(payment, "amount", BigDecimal.valueOf(1000));
        setField(payment, "cardNumber", "1234567812345678");
        setField(payment, "cardExpiry", "0826");

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertThat(violations).isEmpty();
    }

    @Test
    void invalidCardNumber_shouldFailValidation() throws Exception {
        Payment payment = new Payment();
        setField(payment, "amount", BigDecimal.valueOf(1000));
        setField(payment, "cardNumber", "1234"); // 不正な桁数
        setField(payment, "cardExpiry", "0826");

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("cardNumber"));
    }

    @Test
    void negativeAmount_shouldFailValidation() throws Exception {
        Payment payment = new Payment();
        setField(payment, "amount", BigDecimal.valueOf(-100));
        setField(payment, "cardNumber", "1234567812345678");
        setField(payment, "cardExpiry", "0826");

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("amount"));
    }

    @Test
    void invalidExpiry_shouldFailValidation() throws Exception {
        Payment payment = new Payment();
        setField(payment, "amount", BigDecimal.valueOf(1000));
        setField(payment, "cardNumber", "1234567812345678");
        setField(payment, "cardExpiry", "0120"); // 過去の年月

        Set<ConstraintViolation<Payment>> violations = validator.validate(payment);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("cardExpiry"));
    }
}
