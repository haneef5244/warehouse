package com.mynic.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableJpaRepositories("com.mynic.warehouse")
@EntityScan("com.mynic.warehouse")
@SpringBootApplication
public class WarehouseApplication {


	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
