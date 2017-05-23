package com.hrraydov.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hrraydov.config" })
public class TemplateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateServiceApplication.class, args);
	}
}
