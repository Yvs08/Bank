package com.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication
@ComponentScan(basePackages = {"com.bank.repository"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, WebMvcAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = {"com.bank.repository"})
@EntityScan(basePackages = {"com.bank.model"})

public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

}
