package br.com.fiap.restaurant.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class IdentityAccessManagementApplication {

	private IdentityAccessManagementApplication() {}

	public static void main(String[] args) {
		SpringApplication.run(IdentityAccessManagementApplication.class, args);
	}

}
