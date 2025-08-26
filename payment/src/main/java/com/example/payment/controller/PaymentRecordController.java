package com.example.payment.controller;

import com.example.payment.dto.PaymentRecordRequest;
import com.example.payment.dto.PaymentRecordResponse;
import com.example.payment.service.PaymentRecordService;

import jakarta.validation.Valid;

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
    public ResponseEntity<PaymentRecordResponse> selectAllRecords() {
      PaymentRecordResponse payments = paymentRecordService.selectAllRecords();
      return ResponseEntity.ok(payments);
    }

    // 決済履歴一覧取得API
    @PostMapping
    public ResponseEntity<PaymentRecordResponse> selectRecordByPaymentId(@RequestBody @Valid PaymentRecordRequest request) {
      PaymentRecordResponse payment = paymentRecordService.selectRecordByPaymentId(request.getPaymentId());
      return ResponseEntity.ok(payment);
    }
}
