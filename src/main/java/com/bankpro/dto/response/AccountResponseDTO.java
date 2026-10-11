package com.bankpro.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bankpro.enums.AccountStatus;
import com.bankpro.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDTO {

	private Long id;
	
	private String accountNumber;
	
	private AccountType accountType;
	
	private AccountStatus status;
	
	private BigDecimal balance;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private Long customerId;
}
