package br.com.oak.webly.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.core.vo.UsuarioLogado;

public final class AuthenticationUtils {

	private AuthenticationUtils() {
	}

	public static UsuarioLogado getUsuarioLogado() {

		UsuarioLogado usuarioLogado = null;

		final Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();

		if (authentication != null
				&& !(authentication instanceof AnonymousAuthenticationToken)) {
			usuarioLogado = (UsuarioLogado) authentication.getPrincipal();
		}
		return usuarioLogado;
	}

	public static Boolean isAuthenticated() {
		return getUsuarioLogado() != null;
	}

	public static String getNomeUsuarioLogado() {

		String nome = StringUtils.EMPTY;

		final UsuarioLogado usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getNome();
		}
		return nome;
	}

	public static TipoUsuarioEnum getTipoUsuarioEnum() {

		TipoUsuarioEnum tipoUsuarioEnum = null;

		final UsuarioLogado usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			tipoUsuarioEnum = usuarioLogado.getTipoUsuario();
		}
		return tipoUsuarioEnum;
	}
}