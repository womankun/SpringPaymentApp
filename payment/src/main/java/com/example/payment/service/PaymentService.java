package com.example.payment.service;

import com.example.payment.mapper.PaymentMapper;
import com.example.payment.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    public void registerPayment(Payment payment) {      
        String cardNumber = payment.getCardNumber();

        if (cardNumber.equals("4100000000005000")) {
          payment.setStatus("declined");
        } else {
          payment.setStatus("authorised");
        }
        paymentMapper.insertPayment(payment);
    }
}
