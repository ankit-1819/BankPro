package com.bankpro.service;

import java.util.List;

import com.bankpro.dto.request.AccountRequestDTO;
import com.bankpro.dto.response.AccountResponseDTO;

public interface AccountService {

	AccountResponseDTO createAccount(AccountRequestDTO dto);
	
	AccountResponseDTO getAccountById(Long id);
	
	List<AccountResponseDTO> getAllAccounts();
}
