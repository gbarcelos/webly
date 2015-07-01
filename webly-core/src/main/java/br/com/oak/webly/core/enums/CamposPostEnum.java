package br.com.oak.webly.core.enums;

import br.com.oak.core.enums.CamposEnum;

public enum CamposPostEnum implements CamposEnum {
	
	TITULO(45, "postPage.campo.titulo"),
	SUB_TITULO(100, "postPage.campo.subtitulo"),
	POST("postPage.campo.post"),
	PARTE_TITULO(45, "postPage.campo.parteTitulo"),
	AUTOR("postPage.campo.autor"),
	AUTOR_PLACEHOLDER("postPage.campo.autor.placeholder");
	
	private int tamanhoMaximo;
	private String descricao;
	
	private CamposPostEnum(String descricao) {
		this.tamanhoMaximo = 0;
		this.descricao = descricao;
	}

	private CamposPostEnum(int tamanhoMaximo, String descricao) {
		this.tamanhoMaximo = tamanhoMaximo;
		this.descricao = descricao;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	public int getTamanhoMaximo() {
		return tamanhoMaximo;
	}
}
