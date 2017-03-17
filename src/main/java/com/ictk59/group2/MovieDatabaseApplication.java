package com.ictk59.group2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MovieDatabaseApplication extends SpringBootServletInitializer {
	
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		 return application.sources(MovieDatabaseApplication.class);
	 }

	public static void main(String[] args) {
		SpringApplication.run(MovieDatabaseApplication.class, args);
	}
}
