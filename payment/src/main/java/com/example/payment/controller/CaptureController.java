package com.example.payment.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.CaptureRequest;
import com.example.payment.dto.CaptureResponse;
import com.example.payment.service.CaptureService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/captures")
public class CaptureController {
  private final CaptureService captureService;

  public CaptureController(CaptureService captureService) {
      this.captureService = captureService;
  }

  // 売り上げAPI
  @PostMapping
  public ResponseEntity<CaptureResponse> registerCapture(@Valid @RequestBody CaptureRequest captureRequest) {
      CaptureResponse response = captureService.registerCapture(captureRequest.getPaymentId());
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }
}