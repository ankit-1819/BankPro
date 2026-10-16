package com.bankpro.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {

	@NotNull(message = "Source account ID is required")
	@Positive(message = "Source account ID must be positive")
	private Long sourceAccountId;
	
	@NotNull(message = "Destination account ID is required")
	@Positive(message = "Destination account ID must be positive")
	private Long destinationAccountId;
	
	@NotNull(message = "Transfer amount is required")
	@Positive(message = "Transfer amount must be greater than zero")
	private BigDecimal amount;
}
