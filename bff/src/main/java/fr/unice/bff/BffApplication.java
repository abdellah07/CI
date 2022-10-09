package fr.unice.bff;

import fr.unice.bff.service.BaseUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BffApplication {
	public static void main(String[] args) {
		BaseUrl.setLocalTrue();
		SpringApplication.run(BffApplication.class, args);
	}

}
