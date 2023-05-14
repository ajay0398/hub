package com.hub;

import com.hub.configuration.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Import(OpenAPIConfiguration.class)
@SpringBootApplication
public class HubBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HubBackendApplication.class, args);
	}

}
