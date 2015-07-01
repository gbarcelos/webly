package br.com.oak.webly.core.util.validadores;

import org.apache.commons.lang.StringUtils;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;

public class UtilValidadorCodigoVerificacao {

	public static boolean isTamanhoValido(final String value) {

		boolean valido = false;

		if (StringUtils.isNotBlank(value)) {

			if (value.length() == CamposUsuarioEnum.CODIGO_VERIFICACAO
					.getTamanhoMaximo()) {
				valido = true;
			}
		}
		return valido;
	}

	public static boolean isTamanhoInvalido(final String value) {
		return !isTamanhoValido(value);
	}
}