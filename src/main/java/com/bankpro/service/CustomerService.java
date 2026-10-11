package com.bankpro.service;

import java.util.List;

import com.bankpro.dto.request.CustomerRequestDTO;
import com.bankpro.dto.response.CustomerResponseDTO;

public interface CustomerService {
	
	CustomerResponseDTO createCustomer(CustomerRequestDTO dto);
	
	CustomerResponseDTO getCustomerById(Long id);
	
	List<CustomerResponseDTO> getAllCustomers();
	
	CustomerResponseDTO updateCustomer(Long id,CustomerRequestDTO dto);
	
	void deleteCustomer(Long id);

}
