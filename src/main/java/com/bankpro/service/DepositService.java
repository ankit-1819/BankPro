package com.bankpro.service;

import com.bankpro.dto.request.DepositRequestDTO;
import com.bankpro.dto.response.TransactionResponseDTO;

public interface DepositService {

	public TransactionResponseDTO deposit(DepositRequestDTO dto);
}
