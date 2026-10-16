package com.bankpro.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bankpro.enums.TransactionStatus;
import com.bankpro.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bank_transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique = true)
	@NotBlank(message = "Transaction reference is required")
	private String transactionReference;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Transaction type is required")
	private TransactionType transactionType;
	
	@Column(precision = 19,scale = 2)
	@NotNull(message = "Transaction amount is required")
	@Positive(message = "Transaction amount must be greater than zero")
	private BigDecimal amount;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Transaction status is required")
	private TransactionStatus transactionStatus;
	
	@CreatedDate
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_account_id",nullable = true)
	private Account sourceAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destination_account_id",nullable = false)
	private Account destinationAccount;
}
