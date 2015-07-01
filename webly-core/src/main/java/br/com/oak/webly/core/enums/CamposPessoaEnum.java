package br.com.oak.webly.core.enums;

import br.com.oak.core.enums.CamposEnum;

public enum CamposPessoaEnum implements CamposEnum {

	NOME(3, 50, "pessoaPage.campo.nomePessoa"), EMAIL(50, "pessoaPage.campo.email");

	private CamposPessoaEnum(int tamanhoMaximo, String descricao) {
		this.tamanhoMinimo = 0;
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}

	private CamposPessoaEnum(int tamanhoMinimo, int tamanhoMaximo,
			String descricao) {
		this.tamanhoMinimo = tamanhoMinimo;
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}

	private int tamanhoMinimo;
	private int tamanhoMaximo;
	private String descricao;

	public int getTamanhoMinimo() {
		return tamanhoMinimo;
	}

	public int getTamanhoMaximo() {
		return tamanhoMaximo;
	}

	public String getDescricao() {
		return descricao;
	}
}