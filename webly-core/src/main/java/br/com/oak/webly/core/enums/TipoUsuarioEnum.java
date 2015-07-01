package br.com.oak.webly.core.enums;

public enum TipoUsuarioEnum {

	SUPER_ADMINISTRATIVO(1L, "Super Administrativo", "ROLE_SP_ADMIN"), ADMINISTRATIVO(
			2L, "Administrativo", "ROLE_ADMIN"), VISITANTE(3L, "Visitante",
			"ROLE_VISITANTE"), COLABORADOR(4L, "Colaborador", "ROLE_COLAB");

	private TipoUsuarioEnum(final Long codigo, final String descricao,
			final String role) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.role = role;
	}

	private Long codigo;

	private String descricao;

	private String role;

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getRole() {
		return role;
	}

	public static TipoUsuarioEnum getTipoUsuarioByRole(final String role) {

		TipoUsuarioEnum tipo = null;

		for (final TipoUsuarioEnum atual : TipoUsuarioEnum.values()) {
			if (atual.getRole().equals(role)) {
				tipo = atual;
				break;
			}
		}
		return tipo;
	}

	public static TipoUsuarioEnum getTipoUsuarioByDescricao(
			final String descricao) {

		TipoUsuarioEnum tipo = null;

		for (final TipoUsuarioEnum atual : TipoUsuarioEnum.values()) {
			if (atual.getDescricao().equals(descricao)) {
				tipo = atual;
				break;
			}
		}
		return tipo;
	}
}