package br.com.apicadel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.apicadel.services.EmailService;
import br.com.apicadel.services.SmtpEmailService;

@Configuration
public class EmailConfig {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
