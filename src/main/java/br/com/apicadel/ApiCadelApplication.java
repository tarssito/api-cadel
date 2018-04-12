package br.com.apicadel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiCadelApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiCadelApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}
}
