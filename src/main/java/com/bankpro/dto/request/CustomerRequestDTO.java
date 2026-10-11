package com.bankpro.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

	@NotBlank(message = "First name is required")
	@Size(min = 2,max = 15)
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 2,max = 15)
	private String lastName;

	@Email(message = "Proper email is required")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be a valid 10-digit Indian mobile number")
	private String phone;

	@NotBlank(message = "Date of Birth is required")
	private String dateOfBirth;

	@NotBlank(message = "Address name is required")
	@Size(min = 2,max = 50)
	private String address;
}
