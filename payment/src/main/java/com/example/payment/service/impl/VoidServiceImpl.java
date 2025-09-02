package com.example.payment.service.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.example.payment.dto.VoidResponse;
import com.example.payment.mapper.VoidMapper;
import com.example.payment.model.Payment;
import com.example.payment.service.VoidService;

@Service
class VoidServiceImpl implements VoidService {
    private final VoidMapper voidMapper;

    public VoidServiceImpl(VoidMapper voidMapper) {
        this.voidMapper = voidMapper;
    }

    @Override
    public VoidResponse registerVoid(String paymentId) {
      // レコードセレクト
      // レコードあるかつstatusがauthorizedであればupdate
      // それ以外は失敗メッセージリターン
      String status;
      String message;
      Payment payment = new Payment();
      status = "voided";
      message = "オーソリ取り消し完了しました。";
      payment.setStatus(status);
      payment.setVoidedAt(new Timestamp(System.currentTimeMillis()));
      voidMapper.registerVoid(paymentId, payment.getStatus(), payment.getVoidedAt());

      return new VoidResponse(paymentId, status, message);
    }
}
