package br.com.oak.webly.core.enums;

import br.com.oak.core.enums.CamposEnum;

public enum CamposUsuarioEnum implements CamposEnum {

	NOME_USUARIO(50, "usuarioPage.campo.nomeUsuario"),
	
	NOME_PUBLICO_USUARIO(50, "usuarioPage.campo.nomePublicoUsuario"),

	EMAIL(50, "usuarioPage.campo.email"),

	SENHA(5, 15, "usuarioPage.campo.senha"),
	
	CONFIRMA_SENHA(5, 15, "usuarioPage.campo.confirmaSenha"),

	NOVA_SENHA(5, 15, "usuarioPage.campo.novaSenha"),

	CONFIRMA_NOVA_SENHA(5, 15, "usuarioPage.campo.confirmaNovaSenha"),	

	NOME_USUARIO_OU_EMAIL(50, "usuarioPage.campo.nomeUsuarioOuEmail"),

	PARTE_NOME(50, "usuarioPage.campo.parteNomeUsuarioPessoa"),

	TIPO_USUARIO("usuarioPage.campo.tipo"),

	USUARIO_ATIVO_INATIVO("usuarioPage.campo.radioInativo"),

	MEMBRO_DESDE("usuarioPage.campo.membroDesde"),
	
	CODIGO_VERIFICACAO(8, "confirmaUsuarioPage.campo.codigoVerificacao");

	private int tamanhoMinimo;
	private int tamanhoMaximo;
	private String descricao;
	
	private CamposUsuarioEnum(int tamanhoMinimo, int tamanhoMaximo, String descricao){
		this.tamanhoMinimo = tamanhoMinimo;
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}	

	private CamposUsuarioEnum(int tamanhoMaximo, String descricao){
		this.tamanhoMinimo = 0;
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}
	
	private CamposUsuarioEnum(String descricao){
		this.tamanhoMinimo = 0;
		this.tamanhoMaximo = 0;
		this.descricao = descricao;
	}

	public int getTamanhoMaximo() {
		return tamanhoMaximo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public int getTamanhoMinimo() {
		return tamanhoMinimo;
	}
}
