package com.bankpro.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bankpro.dto.request.AccountRequestDTO;
import com.bankpro.dto.response.AccountResponseDTO;
import com.bankpro.enums.AccountType;

public interface AccountService {

	AccountResponseDTO createAccount(AccountRequestDTO dto);
	
	AccountResponseDTO getAccountById(Long id);
	
	List<AccountResponseDTO> getAllAccounts();
	
	Page<AccountResponseDTO> getAccountsByType(AccountType accountType, Pageable pageable);
}
