package com.bankpro.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankpro.enums.TransactionStatus;
import com.bankpro.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponseDTO {

	private Long id;
	
	private String transactionReference;
	
	private TransactionType transactionType;
	
	private BigDecimal amount;
	
	private TransactionStatus transactionStatus;
	
	private LocalDateTime createdAt;
	
	private Long sourceAccountId;
	
	private Long destinationAccountId;
}
