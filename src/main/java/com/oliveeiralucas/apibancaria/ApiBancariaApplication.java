package com.oliveeiralucas.apibancaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class ApiBancariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBancariaApplication.class, args);
	}

}
