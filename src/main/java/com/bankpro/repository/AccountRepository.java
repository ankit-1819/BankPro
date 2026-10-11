package com.bankpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bankpro.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "SELECT nextval('account_number_seq')",nativeQuery = true)
	Long getNextAccountNumber();
}
