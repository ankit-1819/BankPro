package com.bankpro.service;

import com.bankpro.dto.request.TransferRequestDTO;
import com.bankpro.dto.response.TransactionResponseDTO;

public interface TransactionService {

	public TransactionResponseDTO transfer(TransferRequestDTO dto);
}
