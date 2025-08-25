package com.ecommerce.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Forced JVM Timezone = " + TimeZone.getDefault().getID());
		SpringApplication.run(UserApplication.class, args);
	}
}