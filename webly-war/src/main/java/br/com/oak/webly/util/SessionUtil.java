package br.com.oak.webly.util;

import org.apache.wicket.protocol.http.WebSession;

public class SessionUtil {

	public static String identificaAgente() {
		final String agente = WebSession.get().getClientInfo().getUserAgent();
		if (agente.contains("MSIE")) {
			return "onblur";
		}
		return "onchange";
	}
}