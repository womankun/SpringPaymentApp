package com.example.payment.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.example.payment.dto.RefundResponse;
import com.example.payment.mapper.PaymentRecordMapper;
import com.example.payment.mapper.RefundMapper;
import com.example.payment.model.Payment;
import com.example.payment.service.RefundService;

@Service
class RefundServiceImpl implements RefundService {
    private final RefundMapper refundMapper;

    private final PaymentRecordMapper paymentRecordMapper;

    public RefundServiceImpl(RefundMapper refundMapper, PaymentRecordMapper paymentRecordMapper) {
        this.refundMapper = refundMapper;
        this.paymentRecordMapper = paymentRecordMapper;
    }

    @Override
    public RefundResponse registerRefund(String paymentId) {
      String status;
      String message;
      Payment paymentRecord = paymentRecordMapper
        .selectRecordByPaymentId(paymentId)
        .stream()
        .findFirst()
        .orElse(null);

      boolean canRefund = canRefund(
        paymentRecord.getStatus(), 
        paymentRecord.getRefundedAt()
      );

      if (canRefund) {
        Payment payment = new Payment();
        status = "refunded";
        message = "売り上げ取り消し完了しました。";
        payment.setStatus(status);
        payment.setRefundedAt(new Timestamp(System.currentTimeMillis()));
        refundMapper.registerRefund(paymentId, payment.getStatus(), payment.getRefundedAt());
      } else {
        status = "failed";
        message = "売り上げ取り消しに失敗しました。";
      }

      return new RefundResponse(paymentId, status, message);
    }

    private boolean canRefund(String recordStatus, Timestamp refundedAt) {
        return "captured".equalsIgnoreCase(recordStatus) && refundedAt == null;
    }

}
