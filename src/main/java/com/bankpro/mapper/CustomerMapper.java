package com.bankpro.mapper;

import org.springframework.stereotype.Component;

import com.bankpro.dto.request.CustomerRequestDTO;
import com.bankpro.dto.response.CustomerResponseDTO;
import com.bankpro.entity.Customer;

@Component
public class CustomerMapper {

	public Customer toEntity(CustomerRequestDTO dto) {
		
		Customer customer = new Customer();
		
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getEmail());
		customer.setAddress(dto.getAddress());
		customer.setDateOfBirth(dto.getDateOfBirth());
		customer.setPhone(dto.getPhone());
		
		return customer;
	}
	
	public CustomerResponseDTO toDto(Customer customer) {
		
		CustomerResponseDTO dto = new CustomerResponseDTO();
		
		dto.setId(customer.getId());
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setEmail(customer.getEmail());
		dto.setPhone(customer.getPhone());
		dto.setAddress(customer.getAddress());
		dto.setDateOfBirth(customer.getDateOfBirth());
		
		return dto;
	}
}
