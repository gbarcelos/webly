package br.com.oak.webly.core.util.validadores;

import org.apache.commons.lang.StringUtils;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;

public class UtilValidadorPassword {

	public static boolean isTamanhoPasswordValido(final String value) {

		boolean passwordValido = false;

		if (StringUtils.isNotBlank(value)) {

			if (value.length() >= CamposUsuarioEnum.SENHA
					.getTamanhoMinimo()
					&& value.length() <= CamposUsuarioEnum.SENHA
							.getTamanhoMaximo()) {
				passwordValido = true;
			}
		}
		return passwordValido;
	}

	public static boolean isTamanhoInvalido(final String value) {
		return !isTamanhoPasswordValido(value);
	}
}