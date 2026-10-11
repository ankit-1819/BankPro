package com.bankpro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankpro.dto.request.CustomerRequestDTO;
import com.bankpro.dto.response.ApiResponse;
import com.bankpro.dto.response.CustomerResponseDTO;
import com.bankpro.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> createCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO){
		
		CustomerResponseDTO savedCustomer = customerService.createCustomer(requestDTO);
		
		ApiResponse<CustomerResponseDTO> response = new ApiResponse<CustomerResponseDTO>(true, "Customer created successfully", savedCustomer);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> getCustomerById(@PathVariable Long id){
		
		CustomerResponseDTO customer = customerService.getCustomerById(id);
		
		ApiResponse<CustomerResponseDTO> response = new ApiResponse<CustomerResponseDTO>(true, "Customer fetched successfully", customer);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<CustomerResponseDTO>>> getAllCustomer(){
		
		List<CustomerResponseDTO> customers = customerService.getAllCustomers();
		
		ApiResponse<List<CustomerResponseDTO>> response = new ApiResponse<List<CustomerResponseDTO>>(true, "Customers fetched successfully", customers);
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponseDTO>> updateCustomer(@PathVariable Long id,@Valid @RequestBody CustomerRequestDTO requestDTO){
		
		CustomerResponseDTO updatedCustomer = customerService.updateCustomer(id, requestDTO);
		
		ApiResponse<CustomerResponseDTO> response = new ApiResponse<CustomerResponseDTO>(true, "Customer updated successfully", updatedCustomer);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id){
		
		customerService.deleteCustomer(id);
		
		ApiResponse<Void> response = new ApiResponse<Void>(true, "Customer deleted successfully", null);
		
		return ResponseEntity.ok(response);
	}
}
