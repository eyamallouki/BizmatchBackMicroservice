package com.esprit.Bizmatch.User.BizmatchUserAuthentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BizmatchUserAuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizmatchUserAuthentificationApplication.class, args);
	}

}
