package com.example.payment.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.example.payment.dto.CaptureResponse;
import com.example.payment.mapper.PaymentRecordMapper;
import com.example.payment.mapper.CaptureMapper;
import com.example.payment.model.Payment;
import com.example.payment.service.CaptureService;

@Service
class CaptureServiceImpl implements CaptureService {
    private final CaptureMapper captureMapper;

    private final PaymentRecordMapper paymentRecordMapper;

    public CaptureServiceImpl(CaptureMapper captureMapper, PaymentRecordMapper paymentRecordMapper) {
        this.captureMapper = captureMapper;
        this.paymentRecordMapper = paymentRecordMapper;
    }

    @Override
    public CaptureResponse registerCapture(String paymentId) {
      String status;
      String message;
      Payment paymentRecord = paymentRecordMapper
        .selectRecordByPaymentId(paymentId)
        .stream()
        .findFirst()
        .orElse(null);

      boolean canCapture = canCapture(
        paymentRecord.getStatus(), 
        paymentRecord.getCapturedAt()
      );

      if (canCapture) {
        Payment payment = new Payment();
        status = "captured";
        message = "売り上げ完了しました。";
        payment.setStatus(status);
        payment.setCapturedAt(new Timestamp(System.currentTimeMillis()));
        captureMapper.registerCapture(paymentId, payment.getStatus(), payment.getCapturedAt());
      } else {
        status = "failed";
        message = "売り上げに失敗しました。";
      }

      return new CaptureResponse(paymentId, status, message);
    }

    private boolean canCapture(String recordStatus, Timestamp capturedAt) {
        return "authorised".equalsIgnoreCase(recordStatus) && capturedAt == null;
    }

}
