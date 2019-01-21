package com.mobicule.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {
		
		System.out.println("Hello World");
		SpringApplication.run(MySpringBootApplication.class, args);
		
	}
	
}
