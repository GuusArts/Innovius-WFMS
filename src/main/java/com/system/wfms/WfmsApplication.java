package com.system.wfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class WfmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WfmsApplication.class, args);
	}



}
