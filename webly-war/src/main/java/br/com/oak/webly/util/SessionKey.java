package br.com.oak.webly.util;

public enum SessionKey {

	MENU_USUARIO_LOGADO,
	ULTIMOS_POSTS;

	public String getKey() {
		return this.name();
	}
}