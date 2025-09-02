package com.example.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.VoidRequest;
import com.example.payment.dto.VoidResponse;
import com.example.payment.service.VoidService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voids")
public class VoidController {
    private final VoidService voidService;

    public VoidController(VoidService voidService) {
        this.voidService = voidService;
    }

    // オーソリ取り消しAPI
    @PostMapping
    public ResponseEntity<VoidResponse> registerVoid(@Valid @RequestBody VoidRequest voidRequest) {
        VoidResponse response = voidService.registerVoid(voidRequest.getPaymentId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
