package br.com.apicadel.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.apicadel.domain.Aula;

public interface EmailService {

	void sendOrderConfirmationEmail(Aula aula);

	void sendEmail(SimpleMailMessage msg);
}
