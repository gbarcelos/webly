package br.com.oak.webly.pages.validator;

import org.apache.wicket.validation.IValidatable;

import br.com.oak.webly.core.util.validadores.UtilValidadorCodigoVerificacao;
import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class CodigoVerificacaoValidator extends ValidadorCampoString {

	private static final long serialVersionUID = -2161547780151128557L;

	private String msgErro;

	public CodigoVerificacaoValidator(final String msgErro) {
		this.msgErro = msgErro;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {
		if (UtilValidadorCodigoVerificacao.isTamanhoInvalido(validatable
				.getValue())) {

			addErro(validatable, msgErro);
		}
	}
}