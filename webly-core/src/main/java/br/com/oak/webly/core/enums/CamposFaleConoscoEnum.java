package br.com.oak.webly.core.enums;

import br.com.oak.core.enums.CamposEnum;

public enum CamposFaleConoscoEnum implements CamposEnum {

	NOME(50, "faleConoscoPage.campo.nome"), EMAIL(50,
			"faleConoscoPage.campo.email"), ASSUNTO(80,
			"faleConoscoPage.campo.assunto"), MENSAGEM(1000,
			"faleConoscoPage.campo.mensagem");

	private int tamanhoMaximo;
	private String descricao;

	private CamposFaleConoscoEnum(int tamanhoMaximo, String descricao) {
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}

	private CamposFaleConoscoEnum(String descricao) {
		this.descricao = descricao;
	}

	public int getTamanhoMaximo() {
		return tamanhoMaximo;
	}

	public String getDescricao() {
		return descricao;
	}
}