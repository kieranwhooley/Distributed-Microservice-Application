package edu.ait.stateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("edu.ait.stateservice.dto")
public class StateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StateServiceApplication.class, args);
	}

}
