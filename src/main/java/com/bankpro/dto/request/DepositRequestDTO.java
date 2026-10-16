package com.bankpro.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequestDTO {

	@NotNull(message = "Account id is mandatory")
	@Positive(message = "Account id must be positive")
	private Long accountId;
	
	@NotNull(message = "Money is mandatory")
	@Positive(message = "Deposit amount must be greater than zero")
	private BigDecimal amount;
}
