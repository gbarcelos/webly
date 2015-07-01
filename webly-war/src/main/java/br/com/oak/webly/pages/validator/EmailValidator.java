package br.com.oak.webly.pages.validator;

import org.apache.wicket.validation.IValidatable;

import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.util.validadores.UtilValidadorEmail;
import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class EmailValidator extends ValidadorCampoString {

	private static final long serialVersionUID = 8004854071849577937L;

	private String msgErroTamanho;

	public EmailValidator(String msgErroTamanho) {
		this.msgErroTamanho = msgErroTamanho;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {

		if (UtilValidadorEmail.isTamanhoEmailInvalido(validatable.getValue())) {

			addErro(validatable, msgErroTamanho);

		} else if (UtilValidadorEmail.isEmailInvalido(validatable.getValue())) {

			addErro(validatable, ResourceUtil.recuperaMensagemErro(
					MensagemErro.EMAIL_INVALIDO.getCodigo(),
					validatable.getValue()));
		}
	}
}