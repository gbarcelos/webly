package br.com.oak.webly.core.enums;

import br.com.oak.core.model.StringCodeDataEnum;

public enum SimNaoEnum implements StringCodeDataEnum {

	SIM("S", "Sim", true),

	NAO("N", "Não", false);

	private String codigo;

	private String descricao;

	private Boolean boolSimNao;

	private SimNaoEnum(String codigo, String descricao, Boolean boolSimNao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.boolSimNao = boolSimNao;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Boolean getBoolSimNao() {
		return boolSimNao;
	}
}