package br.com.apicadel.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.apicadel.domain.Aula;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Aula aula) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(aula);
		sendEmail(sm);
	}

	private SimpleMailMessage prepareSimpleMailMessageFromPedido(Aula aula) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("tarssito@hotmail.com"); // Alterar para e-mail do professor
		sm.setFrom(sender);
		sm.setSubject("CADEL - Fechamento de Aula: " + aula.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(aula.toString());
		return sm;
	}

}
