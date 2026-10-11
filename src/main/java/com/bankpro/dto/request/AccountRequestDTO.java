package com.bankpro.dto.request;

import com.bankpro.enums.AccountType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDTO {

	@NotNull(message = "Account type is required")
	private AccountType accountType;
	
	@NotNull(message = "Customer ID is required")
	@Positive(message = "Customer ID must be positive")
	private Long customerId;
}
