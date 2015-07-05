package br.com.oak.webly.core.vo;

import java.io.Serializable;

public class NovoUsuarioVo implements Serializable {

	private static final long serialVersionUID = 6824464672061264788L;

	private String nome;

	private String email;

	private String password;

	private String confirmPassword;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}