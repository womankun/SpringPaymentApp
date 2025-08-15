package com.example.payment.service;

import com.example.payment.dto.PaymentResponse;
import com.example.payment.mapper.PaymentMapper;
import com.example.payment.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse registerPayment(Payment payment) {      
        String paymentId = payment.getPaymentId();
        String cardNumber = payment.getCardNumber();
        String status;
        String message;

        if (cardNumber.equals("4100000000005000")) {
            status = "declined";
            message = "このカードは拒否されました。別のカードをご利用ください。";
        } else {
            status = "authorised";
            message = "決済が承認されました。";
        }
        payment.setStatus(status);
        paymentMapper.insertPayment(payment);

        return new PaymentResponse(paymentId, status, message);
    }
}
