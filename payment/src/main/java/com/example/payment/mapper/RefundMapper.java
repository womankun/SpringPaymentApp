package com.example.payment.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RefundMapper {
    void registerRefund(
      @Param("paymentId") String paymentId,
      @Param("status") String status,
      @Param("refundedAt") Timestamp voidedAt
    );
}
