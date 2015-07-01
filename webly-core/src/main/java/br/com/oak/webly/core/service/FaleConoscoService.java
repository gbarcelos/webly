package br.com.oak.webly.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.vo.FaleConoscoVo;
import br.com.oak.webly.core.vo.MensagemVo;

@Service("faleConoscoService")
public class FaleConoscoService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private String emailDestinatarioNome;

	@Autowired
	private String emailDestinatarioEndereco;

	public void enviar(final FaleConoscoVo faleConosco) {
		try {

			final MensagemVo mensagem = new MensagemVo();

			popularAssunto(faleConosco, mensagem);
			popularMensagem(faleConosco, mensagem);

			mensagem.setNomeDestinatario(emailDestinatarioNome);
			mensagem.setEmailDestinatario(emailDestinatarioEndereco);

			emailService.sendEmailDoSistemaPara(mensagem);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void popularAssunto(final FaleConoscoVo faleConosco,
			final MensagemVo msg) {

		final StringBuilder assunto = new StringBuilder();
		assunto.append("E-mail de ");
		assunto.append(faleConosco.getEmail());
		assunto.append(", assunto: ");
		assunto.append(faleConosco.getAssunto());

		msg.setAssunto(assunto.toString());
	}

	private void popularMensagem(final FaleConoscoVo faleConosco,
			MensagemVo mensagem) {

		final StringBuilder msg = new StringBuilder();

		msg.append("Essa é uma mensagem automatizada, por favor não responda.");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Uma nova mensagem foi enviada através do fale conosco:");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Nome: ");
		msg.append(faleConosco.getNome());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Email: ");
		msg.append(faleConosco.getEmail());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Assunto: ");
		msg.append(faleConosco.getAssunto());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Mensagem: ");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(faleConosco.getMensagem());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		mensagem.setTextoMensagem(msg.toString());
	}
}