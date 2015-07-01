package br.com.oak.webly.core.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oak.webly.core.util.AutenticaUsuario;
import br.com.oak.webly.core.vo.MensagemVo;

@Service("emailService")
public class EmailService implements Serializable {

	private static final long serialVersionUID = -6556009965348356064L;

	@Autowired
	private String emailSmtpHost;

	@Autowired
	private Integer emailSmtpPort;

	@Autowired
	private String emailRemetenteEndereco;

	@Autowired
	private String emailRemetenteNome;

	@Autowired
	private String emailRemetenteSenha;

	public void sendEmailDoSistemaPara(final MensagemVo mensagem)
			throws MessagingException, UnsupportedEncodingException {

		final Properties config = System.getProperties();
		config.put("mail.smtp.host", emailSmtpHost);
		config.put("mail.smtp.port", emailSmtpPort);
		config.put("mail.smtp.auth", "true");

		final Session sessao = Session.getInstance(config,
				new AutenticaUsuario(emailRemetenteEndereco,
						emailRemetenteSenha));

		final MimeMessage mimeMessage = new MimeMessage(sessao);
		final String encodingOptions = "text/html; charset=UTF-8";

		mimeMessage.setFrom(new InternetAddress(emailRemetenteEndereco,
				emailRemetenteNome));
		mimeMessage.setRecipients(
				Message.RecipientType.TO,
				new InternetAddress[] { new InternetAddress(mensagem
						.getEmailDestinatario()) });
		mimeMessage.setSubject(mensagem.getAssunto());
		mimeMessage.setSentDate(Calendar.getInstance().getTime());
		mimeMessage.setHeader("Content-Type", encodingOptions);

		popularCorpoMensagem(mensagem, mensagem.getTextoMensagem(),
				mimeMessage, encodingOptions);

		Transport.send(mimeMessage);
	}

	private void popularCorpoMensagem(final MensagemVo mensagem,
			final String texto, final MimeMessage m,
			final String encodingOptions) throws MessagingException {

		final Multipart corpo = new MimeMultipart();

		popularTexto(texto, encodingOptions, corpo);

		m.setContent(corpo, encodingOptions);
	}

	private void popularTexto(final String texto, final String encodingOptions,
			final Multipart corpo) throws MessagingException {

		final MimeBodyPart partTexto = new MimeBodyPart();
		partTexto.setContent(texto, encodingOptions);
		corpo.addBodyPart(partTexto);
	}
}