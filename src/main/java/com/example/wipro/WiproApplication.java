package com.example.wipro;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class WiproApplication {
	@Autowired
	DataSource ds;

	public static void main(String[] args) {
		SpringApplication.run(WiproApplication.class, args);
		System.out.println("Hi spring");
		
	}
	
	

}
