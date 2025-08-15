package com.example.payment.validation;

import java.time.YearMonth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpiryValidator implements ConstraintValidator<ValidExpiry, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches("^(0[1-9]|1[0-2])[0-9]{2}$")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("有効期限はMMYY形式で入力してください")
                   .addConstraintViolation();
            return false;
        }

        int month = Integer.parseInt(value.substring(0, 2));
        int year = Integer.parseInt(value.substring(2, 4)) + 2000;

        YearMonth expiry = YearMonth.of(year, month);
        YearMonth now = YearMonth.now();

        return !expiry.isBefore(now);
    }
}
