package edu.ait.capitalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("edu.ait.capitalservice.dto")
public class CapitalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapitalServiceApplication.class, args);
	}

}
