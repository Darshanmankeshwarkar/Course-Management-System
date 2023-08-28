package com.curdoperations.curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.curdoperations.curd.repository")
@EntityScan(basePackages = "com.curdoperations.curd.model")
@SpringBootApplication(scanBasePackages = "com.curdoperations.curd")
@ComponentScan(basePackages = "com.curdoperations")
public class CurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdApplication.class, args);
	}

}
