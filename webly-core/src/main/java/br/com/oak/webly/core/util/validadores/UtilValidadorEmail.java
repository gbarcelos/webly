package br.com.oak.webly.core.util.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;

public class UtilValidadorEmail {

	private static Pattern pattern;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	static {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public static boolean isEmailValido(final String value) {

		boolean emailValido = false;

		if (StringUtils.isNotBlank(value)) {

			if (isTamanhoEmailValido(value)) {

				Matcher matcher = pattern.matcher(value);

				emailValido = matcher.matches();
			}
		}
		return emailValido;
	}
	
	public static boolean isEmailInvalido(final String value) {
		return !isEmailValido(value);
	}
	
	public static boolean isTamanhoEmailValido(final String value) {

		boolean tamanhoEmailValido = false;

		if (StringUtils.isNotBlank(value)) {

			if (value.length() <= CamposUsuarioEnum.EMAIL.getTamanhoMaximo()) {

				tamanhoEmailValido = true;
			}
		}
		return tamanhoEmailValido;
	}

	public static boolean isTamanhoEmailInvalido(final String value) {
		return !isTamanhoEmailValido(value);
	}
}