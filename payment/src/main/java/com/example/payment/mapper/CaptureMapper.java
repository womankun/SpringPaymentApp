package com.example.payment.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CaptureMapper {
    void registerCapture(
      @Param("paymentId") String paymentId,
      @Param("status") String status,
      @Param("capturedAt") Timestamp voidedAt
    );
}
