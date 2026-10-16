package com.bankpro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankpro.dto.request.TransferRequestDTO;
import com.bankpro.dto.response.TransactionResponseDTO;
import com.bankpro.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	
	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<TransactionResponseDTO> transfer(@Valid @RequestBody TransferRequestDTO dto ) {
		
		TransactionResponseDTO transaction = transactionService.transfer(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}
}
