package com.bankpro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankpro.dto.request.DepositRequestDTO;
import com.bankpro.dto.response.TransactionResponseDTO;
import com.bankpro.service.DepositService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

	private final DepositService depositService;

	public DepositController(DepositService depositService) {
		super();
		this.depositService = depositService;
	}
	
	@PostMapping
	public ResponseEntity<TransactionResponseDTO> deposit(@Valid @RequestBody DepositRequestDTO dto){
		
		TransactionResponseDTO transaction = depositService.deposit(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}
}
