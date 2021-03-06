package com.handson.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootHandsonTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHandsonTestApplication.class, args);
	}

}
