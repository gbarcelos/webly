package br.com.oak.webly.core.vo;

import java.io.Serializable;

public class ConfirmaUsuarioVo implements Serializable {

	private static final long serialVersionUID = 3183612986653291420L;

	private String nomeUsuarioOuEmail;

	private String codigoVerificacao;

	private String password;

	public ConfirmaUsuarioVo() {
	}

	public ConfirmaUsuarioVo(final String nomeUsuarioOuEmail,
			final String codigoVerificacao) {
		this.nomeUsuarioOuEmail = nomeUsuarioOuEmail;
		this.codigoVerificacao = codigoVerificacao;
	}

	public String getNomeUsuarioOuEmail() {
		return nomeUsuarioOuEmail;
	}

	public void setNomeUsuarioOuEmail(String nomeUsuarioOuEmail) {
		this.nomeUsuarioOuEmail = nomeUsuarioOuEmail;
	}

	public String getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(String codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}