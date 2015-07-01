package br.com.oak.webly.pages.validator;

import org.apache.wicket.validation.IValidatable;

import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.util.validadores.UtilValidatorNomeUsuarioEmail;
import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class NomeUsuarioEmailValidator extends ValidadorCampoString {

	private static final long serialVersionUID = -8760552125867427672L;

	private String msgErro;

	public NomeUsuarioEmailValidator(String msgErro) {
		this.msgErro = msgErro;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {

		if (UtilValidatorNomeUsuarioEmail
				.isTamanhoNomeUsuarioEmailInValido(validatable.getValue())) {

			addErro(validatable, msgErro);

		} else if (UtilValidatorNomeUsuarioEmail
				.isNomeUsuarioEmailInValido(validatable.getValue())) {

			addErro(validatable, ResourceUtil.recuperaMensagemErro(
					MensagemErro.NOME_USUARIO_OU_EMAIL_INVALIDO.getCodigo(),
					validatable.getValue()));
		}
	}
}