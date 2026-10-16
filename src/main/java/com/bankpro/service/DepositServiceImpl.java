package com.bankpro.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankpro.dto.request.DepositRequestDTO;
import com.bankpro.dto.response.TransactionResponseDTO;
import com.bankpro.entity.Account;
import com.bankpro.entity.Transaction;
import com.bankpro.enums.AccountStatus;
import com.bankpro.enums.TransactionStatus;
import com.bankpro.enums.TransactionType;
import com.bankpro.exception.AccountNotFoundException;
import com.bankpro.mapper.TransactionMapper;
import com.bankpro.repository.AccountRepository;
import com.bankpro.repository.TransactionRepository;

@Service
public class DepositServiceImpl implements DepositService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
	
	
	public DepositServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository,
			TransactionMapper transactionMapper) {
		super();
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
	}

	
	@Override
	@Transactional
	public TransactionResponseDTO deposit(DepositRequestDTO dto) {
		
		Long accountId = dto.getAccountId();
		
		Account receiverAccount = accountRepository.findByIdForUpdate(accountId).orElseThrow( () -> new AccountNotFoundException("Receiver account id is inavlid: "+dto.getAccountId()));
		if (receiverAccount.getStatus() != AccountStatus.ACTIVE) {
			
			throw new IllegalArgumentException("Receiver Account is not ACTIVE!");
		}
		
		receiverAccount.setBalance(receiverAccount.getBalance().add(dto.getAmount()));
		
		String transactionReference = "TXN-" + UUID.randomUUID().toString();
		
		Transaction transaction = new Transaction();
		
		transaction.setAmount(dto.getAmount());
		transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setTransactionReference(transactionReference);
		transaction.setDestinationAccount(receiverAccount);
		transaction.setSourceAccount(null);
		
		Transaction savedTransaction = transactionRepository.save(transaction);
		
		
		return transactionMapper.toDTO(savedTransaction);
	}

	
}
