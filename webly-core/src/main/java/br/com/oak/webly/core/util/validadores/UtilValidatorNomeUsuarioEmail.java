package br.com.oak.webly.core.util.validadores;

import org.apache.commons.lang.StringUtils;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.util.ConstantesCore;

public class UtilValidatorNomeUsuarioEmail {

	public static boolean isNomeUsuarioEmailValido(final String value) {

		boolean nomeUsuarioEmailValido = false;

		if (isTamanhoNomeUsuarioEmailValido(value)) {

			if (StringUtils.isNotBlank(value) && value.contains(ConstantesCore.SEPERADOR_EMAIL)) {

				if (UtilValidadorEmail.isEmailValido(value)) {
					nomeUsuarioEmailValido = true;
				}

			} else {
				nomeUsuarioEmailValido = true;
			}
		}
		return nomeUsuarioEmailValido;
	}

	public static boolean isTamanhoNomeUsuarioEmailValido(final String value) {

		boolean tamanhoValido = false;

		if (StringUtils.isNotBlank(value)) {

			if (value.length() <= CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL
					.getTamanhoMaximo()) {

				tamanhoValido = true;
			}
		}
		return tamanhoValido;
	}

	public static boolean isNomeUsuarioEmailInValido(final String value) {
		return !isNomeUsuarioEmailValido(value);
	}

	public static boolean isTamanhoNomeUsuarioEmailInValido(final String value) {
		return !isTamanhoNomeUsuarioEmailValido(value);
	}
}