package com.bankpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankpro.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	
}
