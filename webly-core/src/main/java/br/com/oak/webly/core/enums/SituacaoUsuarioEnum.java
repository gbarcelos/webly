package br.com.oak.webly.core.enums;

public enum SituacaoUsuarioEnum {

	CONFIRMADO(1L, "Confirmado"), AGUARDANDO_CONFIRMACAO_INCLUSAO(2L,
			"Aguardando confirmação após inclusão"), AGUARDANDO_CONFIRMACAO_ALTERACAO(
			3L, "Aguardando confirmação após alteração");

	private SituacaoUsuarioEnum(final Long codigo, final String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	private Long codigo;

	private String descricao;

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}