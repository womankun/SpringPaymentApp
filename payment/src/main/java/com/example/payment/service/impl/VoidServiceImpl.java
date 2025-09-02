package com.example.payment.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.example.payment.dto.VoidResponse;
import com.example.payment.mapper.PaymentRecordMapper;
import com.example.payment.mapper.VoidMapper;
import com.example.payment.model.Payment;
import com.example.payment.service.VoidService;

@Service
class VoidServiceImpl implements VoidService {
    private final VoidMapper voidMapper;

    private final PaymentRecordMapper paymentRecordMapper;

    public VoidServiceImpl(VoidMapper voidMapper, PaymentRecordMapper paymentRecordMapper) {
        this.voidMapper = voidMapper;
        this.paymentRecordMapper = paymentRecordMapper;
    }

    @Override
    public VoidResponse registerVoid(String paymentId) {
      String status;
      String message;
      Payment paymentRecord = paymentRecordMapper
        .selectRecordByPaymentId(paymentId)
        .stream()
        .findFirst()
        .orElse(null);

      boolean canVoid = canVoid(
        paymentRecord.getStatus(), 
        paymentRecord.getVoidedAt()
      );

      if (canVoid) {
        Payment payment = new Payment();
        status = "voided";
        message = "オーソリ取り消し完了しました。";
        payment.setStatus(status);
        payment.setVoidedAt(new Timestamp(System.currentTimeMillis()));
        voidMapper.registerVoid(paymentId, payment.getStatus(), payment.getVoidedAt());
      } else {
        status = "failed";
        message = "オーソリ取り消しに失敗しました。";
      }

      return new VoidResponse(paymentId, status, message);
    }

    private boolean canVoid(String recordStatus, Timestamp voidedAt) {
        return "authorised".equalsIgnoreCase(recordStatus) && voidedAt == null;
    }

}
