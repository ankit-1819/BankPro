package com.bankpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bankpro.entity.Account;
import com.bankpro.enums.AccountType;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "SELECT nextval('account_number_seq')",nativeQuery = true)
	Long getNextAccountNumber();
	
	@Query("SELECT a FROM Account a where a.accountType = :accountType")
	Page<Account> findByAccountType(@Param("accountType") AccountType accountType, Pageable pageable);
}
