package com.bankpro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankpro.dto.request.CustomerRequestDTO;
import com.bankpro.dto.response.CustomerResponseDTO;
import com.bankpro.entity.Customer;
import com.bankpro.exception.CustomerNotFoundException;
import com.bankpro.mapper.CustomerMapper;
import com.bankpro.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		super();
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDto) {

		Customer customer = customerMapper.toEntity(requestDto);

		Customer savedCustomer = customerRepository.save(customer);

		CustomerResponseDTO responseDto = customerMapper.toDto(savedCustomer);

		return responseDto;
	}

	@Override
	public CustomerResponseDTO getCustomerById(Long id) {

		Customer customer = customerRepository.findById(id).orElseThrow( () -> new CustomerNotFoundException("Customer not found with id: "+id));

		CustomerResponseDTO responseDTO = customerMapper.toDto(customer);

		return responseDTO;
	}

	@Override
	public List<CustomerResponseDTO> getAllCustomers() {
		
		List<Customer> customers = customerRepository.findAll();
		
		List<CustomerResponseDTO> responseDTO = new ArrayList<CustomerResponseDTO>();
		
		for (Customer customer : customers) {
			
			responseDTO.add(customerMapper.toDto(customer));
		}
		
		return responseDTO;
	}

	@Override
	public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO) {
		
		Customer customer = customerMapper.toEntity(requestDTO);

		Customer existingCustomer = customerRepository.findById(id).orElseThrow( () ->  new CustomerNotFoundException("Customer not found with id: "+id));

			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setPhone(customer.getPhone());
			existingCustomer.setDateOfBirth(customer.getDateOfBirth());
			existingCustomer.setAddress(customer.getAddress());
			
		Customer savedCustomer = customerRepository.save(existingCustomer);
			
		CustomerResponseDTO responseDTO = customerMapper.toDto(savedCustomer);

		return responseDTO;
	}

	@Override
	public void deleteCustomer(Long id) {

		customerRepository.findById(id).orElseThrow( () -> new CustomerNotFoundException("Customer not found with id: "+id));
		
		customerRepository.deleteById(id);
	}

}
