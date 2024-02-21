package com.example.wating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatingApplication.class, args);
	}

}
