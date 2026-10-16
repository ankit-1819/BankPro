package com.bankpro.mapper;

import org.springframework.stereotype.Component;

import com.bankpro.dto.response.TransactionResponseDTO;
import com.bankpro.entity.Transaction;

@Component
public class TransactionMapper {

	public TransactionResponseDTO toDTO (Transaction transaction) {
		
		TransactionResponseDTO dto = new TransactionResponseDTO();
		
		dto.setId(transaction.getId());
		dto.setAmount(transaction.getAmount());
		dto.setCreatedAt(transaction.getCreatedAt());
		dto.setDestinationAccountId(transaction.getDestinationAccount().getId());
		dto.setTransactionReference(transaction.getTransactionReference());
		dto.setTransactionStatus(transaction.getTransactionStatus());
		dto.setTransactionType(transaction.getTransactionType());
		
		if (transaction.getSourceAccount() != null) {
			
			dto.setSourceAccountId(transaction.getSourceAccount().getId());
		}else {
			
			dto.setSourceAccountId(null);
		}
		return dto;
	}
}
