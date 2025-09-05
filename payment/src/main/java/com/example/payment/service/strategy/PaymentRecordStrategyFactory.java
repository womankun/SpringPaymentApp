package com.example.payment.service.strategy;

import org.springframework.stereotype.Component;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.service.strategy.impl.DateOnlyStrategy;
import com.example.payment.service.strategy.impl.PaymentIdAndDateStrategy;
import com.example.payment.service.strategy.impl.PaymentIdOnlyStrategy;

@Component
public class PaymentRecordStrategyFactory {

    private final PaymentIdAndDateStrategy paymentIdAndDateStrategy;
    private final PaymentIdOnlyStrategy paymentIdOnlyStrategy;
    private final DateOnlyStrategy dateOnlyStrategy;

    public PaymentRecordStrategyFactory(
        PaymentIdAndDateStrategy paymentIdAndDateStrategy,
        PaymentIdOnlyStrategy paymentIdOnlyStrategy,
        DateOnlyStrategy dateOnlyStrategy
    ) {
        this.paymentIdAndDateStrategy = paymentIdAndDateStrategy;
        this.paymentIdOnlyStrategy = paymentIdOnlyStrategy;
        this.dateOnlyStrategy = dateOnlyStrategy;
    }

    public PaymentRecordStrategy resolve(PaymentRecordRequest request) {
        boolean hasPaymentId = request.getPaymentId() != null && !request.getPaymentId().isEmpty();
        boolean hasDateRange = request.getStartDate() != null && request.getEndDate() != null;

        if (hasPaymentId && hasDateRange) return paymentIdAndDateStrategy;
        if (hasPaymentId) return paymentIdOnlyStrategy;
        return dateOnlyStrategy;
    }
}
