package br.com.oak.webly.core.vo;

import java.io.Serializable;

import br.com.oak.webly.core.enums.TipoUsuarioEnum;

public class UsuarioLogado implements Serializable {

	private static final long serialVersionUID = 4110059504764272878L;

	private Long codigo;

	private Long codigoPessoa;

	private String nomePessoa;

	private String nome;

	private String nomePublico;

	private String email;

	private String userAgent;

	private String remoteAddress;

	private String dataInclusaoExtenso;

	private TipoUsuarioEnum tipoUsuario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Long codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePublico() {
		return nomePublico;
	}

	public void setNomePublico(String nomePublico) {
		this.nomePublico = nomePublico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public String getDataInclusaoExtenso() {
		return dataInclusaoExtenso;
	}

	public void setDataInclusaoExtenso(String dataInclusaoExtenso) {
		this.dataInclusaoExtenso = dataInclusaoExtenso;
	}
}