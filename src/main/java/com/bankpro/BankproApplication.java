package com.bankpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankproApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankproApplication.class, args);
	}

}
