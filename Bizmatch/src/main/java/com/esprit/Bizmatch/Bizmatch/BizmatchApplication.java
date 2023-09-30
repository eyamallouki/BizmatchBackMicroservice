package com.esprit.Bizmatch.Bizmatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BizmatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizmatchApplication.class, args);
	}

}
