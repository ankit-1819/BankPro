package com.bankpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankpro.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
