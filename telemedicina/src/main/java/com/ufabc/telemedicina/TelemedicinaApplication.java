package com.ufabc.telemedicina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class TelemedicinaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelemedicinaApplication.class, args);
	}

}
