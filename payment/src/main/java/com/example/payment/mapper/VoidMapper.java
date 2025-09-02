package com.example.payment.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VoidMapper {
    void registerVoid(
      @Param("paymentId") String paymentId,
      @Param("status") String status,
      @Param("voidedAt") Timestamp voidedAt
    );
}
