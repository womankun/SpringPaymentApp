package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentRecordService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-records")
public class PaymentRecordController {

    private final PaymentRecordService paymentRecordService;

    public PaymentRecordController(PaymentRecordService paymentRecordService) {
      this.paymentRecordService = paymentRecordService;
    }

    // 決済履歴一覧取得API
    @GetMapping
    public ResponseEntity<List<Payment>> selectAllRecords() {
      List<Payment> payments = paymentRecordService.selectAllRecords();
      return ResponseEntity.ok(payments);
    }
}
