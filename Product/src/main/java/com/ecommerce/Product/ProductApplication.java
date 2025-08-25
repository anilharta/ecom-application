package com.ecommerce.Product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Forced JVM Timezone => " + TimeZone.getDefault().getID());
		SpringApplication.run(ProductApplication.class, args);
	}

}
