package br.com.oak.webly.core.vo;

import java.io.Serializable;

public class LoginVo implements Serializable{

	private static final long serialVersionUID = -4263028414664628007L;

	private String nomeUsuarioOuEmail;
	
	private String password;
	
	public LoginVo() {
	}

	public LoginVo(final String nomeUsuarioOuEmail, final String password) {
		super();
		this.nomeUsuarioOuEmail = nomeUsuarioOuEmail;
		this.password = password;
	}

	public String getNomeUsuarioOuEmail() {
		return nomeUsuarioOuEmail;
	}

	public void setNomeUsuarioOuEmail(String nomeUsuarioOuEmail) {
		this.nomeUsuarioOuEmail = nomeUsuarioOuEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}