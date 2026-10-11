package com.bankpro.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	
	
	public ErrorResponse(boolean success, String message, int status) {

		this.success = success;
		this.message = message;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}
	
	public ErrorResponse(boolean success, String message, int status, Map<String, String> errors) {

		this.success = success;
		this.message = message;
		this.status = status;
		this.errors = errors;
		this.timestamp = LocalDateTime.now();
	}


	private boolean success;
	
	private String message;
	
	private int status;
	
	private LocalDateTime timestamp;
	
	private Map<String, String> errors;
}
