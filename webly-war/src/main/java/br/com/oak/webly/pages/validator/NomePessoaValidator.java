package br.com.oak.webly.pages.validator;

import org.apache.wicket.validation.IValidatable;

import br.com.oak.webly.core.util.validadores.UtilValidadorNomePessoa;
import br.com.oak.wicket.ui.validadores.ValidadorCampoString;

public class NomePessoaValidator extends ValidadorCampoString {

	private static final long serialVersionUID = 4034009542795683559L;

	private String msgErro;

	public NomePessoaValidator(String msgErro) {
		this.msgErro = msgErro;
	}

	@Override
	protected void onValidate(final IValidatable<String> validatable) {

		if (UtilValidadorNomePessoa.isTamanhoNomePessoaInvalido(validatable
				.getValue())) {

			addErro(validatable, msgErro);
		}
	}
}