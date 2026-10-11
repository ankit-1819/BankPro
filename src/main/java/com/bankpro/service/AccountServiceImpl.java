package com.bankpro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankpro.dto.request.AccountRequestDTO;
import com.bankpro.dto.response.AccountResponseDTO;
import com.bankpro.entity.Account;
import com.bankpro.entity.Customer;
import com.bankpro.enums.AccountStatus;
import com.bankpro.exception.AccountNotFoundException;
import com.bankpro.exception.CustomerNotFoundException;
import com.bankpro.mapper.AccountMapper;
import com.bankpro.repository.AccountRepository;
import com.bankpro.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	
	private final CustomerRepository customerRepository;
	
	private final AccountMapper accountMapper;
	
	
	
	public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository,
			AccountMapper accountMapper) {
		super();
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
		this.accountMapper = accountMapper;
	}

	@Override
	public AccountResponseDTO createAccount(AccountRequestDTO dto) {
		
		Customer existingCustomer = customerRepository.findById(dto.getCustomerId()).orElseThrow( () -> new CustomerNotFoundException("Account not found with id: "+dto.getCustomerId()));
		
		Account account = accountMapper.toEntity(dto, existingCustomer);
		
		account.setBalance(BigDecimal.ZERO);
		account.setStatus(AccountStatus.ACTIVE);
		
		Long accountNumber = accountRepository.getNextAccountNumber();
		account.setAccountNumber(String.valueOf(accountNumber));
		
		Account savedAccount = accountRepository.save(account);
		
		AccountResponseDTO responseDto = accountMapper.toDto(savedAccount);
		return responseDto;
	}

	@Override
	public AccountResponseDTO getAccountById(Long id) {
		
		Account existingAccount = accountRepository.findById(id).orElseThrow( () -> new AccountNotFoundException("Account not found with id: "+id));
		
		AccountResponseDTO dto = accountMapper.toDto(existingAccount);
		
		return dto;
	}

	@Override
	public List<AccountResponseDTO> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		
		List<AccountResponseDTO> accountsDtoList = new ArrayList<AccountResponseDTO>();
		
		for (Account account : accounts) {
			
			accountsDtoList.add(accountMapper.toDto(account));
		}
		
		return accountsDtoList;
	}

	
}
