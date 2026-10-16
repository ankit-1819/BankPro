package com.bankpro.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankpro.dto.request.TransferRequestDTO;
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
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final TransactionMapper transactionMapper;
	private final AccountRepository accountRepository;

	public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
			AccountRepository accountRepository) {
		this.transactionRepository = transactionRepository;
		this.transactionMapper = transactionMapper;
		this.accountRepository = accountRepository;
	}

	@Transactional
	public TransactionResponseDTO transfer(TransferRequestDTO dto) {

		Long senderId = dto.getSourceAccountId();
		Long receiverId = dto.getDestinationAccountId();
		Account senderAccount;
		Account receiverAccount;

		if (senderId < receiverId) {

			senderAccount = accountRepository.findByIdForUpdate(senderId)
					.orElseThrow(() -> new AccountNotFoundException(
							"The Sender Account ID is invalid: " + dto.getSourceAccountId()));
			receiverAccount = accountRepository.findByIdForUpdate(receiverId)
					.orElseThrow(() -> new AccountNotFoundException(
							"The Receiver Accound ID is invalid: " + dto.getDestinationAccountId()));

		} else {

			receiverAccount = accountRepository.findByIdForUpdate(receiverId)
					.orElseThrow(() -> new AccountNotFoundException(
							"The Receiver Accound ID is invalid: " + dto.getDestinationAccountId()));
			senderAccount = accountRepository.findByIdForUpdate(senderId)
					.orElseThrow(() -> new AccountNotFoundException(
							"The Sender Account ID is invalid: " + dto.getSourceAccountId()));

		}
		if (senderId.equals(receiverId)) {

			throw new IllegalArgumentException("Source and Destination accounts cannot be the same");
		}

		if (senderAccount.getStatus() != AccountStatus.ACTIVE || receiverAccount.getStatus() != AccountStatus.ACTIVE) {

			throw new IllegalArgumentException("Both accounts must be active to transfer money");
		}

		if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

			throw new IllegalArgumentException("Transfer amount must be greater than zero");
		}

		if (senderAccount.getBalance().compareTo(dto.getAmount()) < 0) {

			throw new IllegalArgumentException("Insufficient account balance");
		}

		senderAccount.setBalance(senderAccount.getBalance().subtract(dto.getAmount()));
		receiverAccount.setBalance(receiverAccount.getBalance().add(dto.getAmount()));

		String transactionReference = "TXN-" + UUID.randomUUID().toString();

		Transaction transaction = new Transaction();

		transaction.setTransactionReference(transactionReference);
		transaction.setTransactionType(TransactionType.TRANSFER);
		transaction.setAmount(dto.getAmount());
		transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
		transaction.setSourceAccount(senderAccount);
		transaction.setDestinationAccount(receiverAccount);

		Transaction savedTransaction = transactionRepository.save(transaction);

		return transactionMapper.toDTO(savedTransaction);
	}

}
