package com.ecommerce.Order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class OrderApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Forced JVM Timezone => " + TimeZone.getDefault().getID());
		SpringApplication.run(OrderApplication.class, args);
	}

}
