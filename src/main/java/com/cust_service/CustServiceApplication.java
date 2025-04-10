package com.cust_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CustServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustServiceApplication.class, args);
	}

}
