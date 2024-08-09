package com.vivace.opensw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OpenswApplication {
	public static void main(String[] args) {
		SpringApplication.run(OpenswApplication.class, args);
	}
}
