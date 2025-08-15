package com.example.payment.mapper;

import com.example.payment.model.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void insertPayment(Payment payment);
}