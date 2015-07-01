package br.com.oak.webly.pages.validator;

import org.apache.wicket.validation.IValidatable;

import br.com.oak.webly.core.util.validadores.UtilValidadorPassword;
import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class PasswordValidator extends ValidadorCampoString {

	private static final long serialVersionUID = 7206236202976945630L;

	private String msgErro;

	public PasswordValidator(String msgErro) {
		this.msgErro = msgErro;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {

		if (UtilValidadorPassword.isTamanhoInvalido(validatable.getValue())) {

			addErro(validatable, msgErro);
		}
	}
}