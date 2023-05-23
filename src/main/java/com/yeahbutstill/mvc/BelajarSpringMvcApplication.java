package com.yeahbutstill.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BelajarSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringMvcApplication.class, args);
	}

}
