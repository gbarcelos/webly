package br.com.oak.webly.core.util.validadores;

import org.apache.commons.lang.StringUtils;

import br.com.oak.webly.core.enums.CamposPessoaEnum;

public class UtilValidadorNomePessoa {

	public static boolean isTamanhoNomePessoaValido(final String value) {

		boolean nomePessoa = false;

		if (StringUtils.isNotBlank(value)) {

			if (value.length() >= CamposPessoaEnum.NOME.getTamanhoMinimo()
					&& value.length() <= CamposPessoaEnum.NOME
							.getTamanhoMaximo()) {
				nomePessoa = true;
			}
		}
		return nomePessoa;
	}

	public static boolean isTamanhoNomePessoaInvalido(final String value) {
		return !isTamanhoNomePessoaValido(value);
	}
}