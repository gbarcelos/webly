package br.com.oak.webly.core.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ResourceUtil {

	private static ResourceBundle bundleMgs;

	private static ResourceBundle bundleLabels;

	public static String recuperaMensagemErro(final String chave,
			final Object... parametros) {

		return formata(getBundleMgs().getString(chave), parametros);
	}
	
	public static String recuperaLabel(final String chave,
			final Object... parametros) {

		return formata(getBundleLabels().getString(chave), parametros);
	}

	private static ResourceBundle getBundleMgs() {

		if (bundleMgs == null) {

			bundleMgs = ResourceBundle.getBundle(ConstantesCore.PACOTE_BASE
					+ ".resource.messages");			
		}
		return bundleMgs;
	}

	private static ResourceBundle getBundleLabels() {

		if (bundleLabels == null) {

			bundleLabels = ResourceBundle.getBundle(ConstantesCore.PACOTE_BASE
					+ ".resource.wicketApplication");
		}
		return bundleLabels;
	}

	private static String formata(final String mensagem,
			final Object... parametros) {
		return MessageFormat.format(mensagem, parametros);
	}
}