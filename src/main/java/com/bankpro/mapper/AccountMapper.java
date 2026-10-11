package com.bankpro.mapper;

import org.springframework.stereotype.Component;

import com.bankpro.dto.request.AccountRequestDTO;
import com.bankpro.dto.response.AccountResponseDTO;
import com.bankpro.entity.Account;
import com.bankpro.entity.Customer;

@Component
public class AccountMapper {

	public Account toEntity (AccountRequestDTO dto, Customer customer) {
		
		Account account = new Account();
		
		account.setAccountType(dto.getAccountType());
		account.setCustomer(customer);
		
		return account;
	}
	
	public AccountResponseDTO toDto (Account account) {
		
		AccountResponseDTO dto = new AccountResponseDTO();
		
		dto.setId(account.getId());
		dto.setAccountNumber(account.getAccountNumber());
		dto.setBalance(account.getBalance());
		dto.setStatus(account.getStatus());
		dto.setAccountType(account.getAccountType());
		dto.setCreatedAt(account.getCreatedAt());
		dto.setUpdatedAt(account.getUpdatedAt());
		dto.setCustomerId(account.getCustomer().getId());
		
		return dto;
		
	}
}
