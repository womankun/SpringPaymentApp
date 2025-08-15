package com.example.payment.mapper;

import com.example.payment.model.Payment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRecordMapper {
    List<Payment> selectAllRecords();
}