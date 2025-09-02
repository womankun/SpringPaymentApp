package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.RefundRequest;
import com.example.payment.dto.RefundResponse;
import com.example.payment.service.RefundService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {
  private final RefundService refundService;

  public RefundController(RefundService refundService) {
      this.refundService = refundService;
  }

  // 売り上げ取り消しAPI
  @PostMapping
  public ResponseEntity<RefundResponse> registerRefund(@Valid @RequestBody RefundRequest refundRequest) {
      RefundResponse response = refundService.registerRefund(refundRequest.getPaymentId());
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}
