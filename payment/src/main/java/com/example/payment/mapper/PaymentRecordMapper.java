package com.example.payment.mapper;

import com.example.payment.model.Payment;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRecordMapper {
    List<Payment> selectAllRecords();
    List<Payment> selectRecordByPaymentId(String paymentId);
    List<Payment> selectRecordByDate(LocalDate startDate, LocalDate endDate);
    List<Payment> selectRecordByPaymentIdAndDate(String paymentId, LocalDate startDate, LocalDate endDate);
}