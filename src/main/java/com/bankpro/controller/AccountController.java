package com.bankpro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankpro.dto.request.AccountRequestDTO;
import com.bankpro.dto.response.AccountResponseDTO;
import com.bankpro.dto.response.ApiResponse;
import com.bankpro.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<AccountResponseDTO>> createAccount (@Valid @RequestBody AccountRequestDTO dto) {
		
		AccountResponseDTO savedAccount = accountService.createAccount(dto);
		
		ApiResponse<AccountResponseDTO> response = new ApiResponse<AccountResponseDTO>(true, "Account created successfully!", savedAccount);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<AccountResponseDTO>> getAccountById(@PathVariable Long id){
		
		AccountResponseDTO account = accountService.getAccountById(id);
		
		ApiResponse<AccountResponseDTO> response = new ApiResponse<AccountResponseDTO>(true, "Account fetched successfully!", account);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<AccountResponseDTO>>> getAllAccounts (){
		
		List<AccountResponseDTO> accounts = accountService.getAllAccounts();
		
		ApiResponse<List<AccountResponseDTO>> response = new ApiResponse<List<AccountResponseDTO>>(true, "Accounts fetched successfully", accounts);
		
		return ResponseEntity.ok(response);
	}

}
