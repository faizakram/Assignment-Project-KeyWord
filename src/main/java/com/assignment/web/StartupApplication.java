package com.assignment.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * StartUp Application
 *
 */
@SpringBootApplication
public class StartupApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartupApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}
}
