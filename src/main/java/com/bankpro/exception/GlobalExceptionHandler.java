package com.bankpro.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bankpro.dto.response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex){
		
		ErrorResponse response = new ErrorResponse(false, ex.getMessage(),404);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors.put(error.getField(), error.getDefaultMessage());
	    }

	    ErrorResponse response = new ErrorResponse(false, "Validation failed", 400, errors);
	    
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex){
		
		ErrorResponse response = new ErrorResponse(false, ex.getMessage(), 404);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException (IllegalArgumentException ex){
		
		ErrorResponse response = new ErrorResponse(false, ex.getMessage(), 400);
		
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException (ConstraintViolationException ex){
		
		String message = ex.getConstraintViolations()
		        .stream()
		        .map(violation -> {
		            String path = violation.getPropertyPath().toString();
		            String parameter = path.substring(path.lastIndexOf('.') + 1);
		            return parameter + ": " + violation.getMessage();
		        })
		        .findFirst()
		        .orElse("Invalid request parameter");
		
		ErrorResponse response = new ErrorResponse(false, message, 400);
		
		return ResponseEntity.badRequest().body(response);
	}
}
