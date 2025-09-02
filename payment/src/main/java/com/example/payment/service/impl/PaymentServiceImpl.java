package com.example.payment.service.impl;

import org.springframework.stereotype.Service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.mapper.PaymentMapper;
import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse registerPayment(PaymentRequest paymentRequest) {      
        Payment payment = toEntity(paymentRequest);
        String cardNumber = payment.getCardNumber();
        String paymentId = payment.getPaymentId();
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
        paymentMapper.registerPayment(payment);

        return new PaymentResponse(paymentId, status, message);
    }

    
    private Payment toEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setCardExpiry(paymentRequest.getCardExpiry());
        return payment;
    }
}
