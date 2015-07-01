package br.com.oak.webly.core.model.dbwebly;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.oak.core.entidade.ExclusaoLogica;
import br.com.oak.core.enums.StatusRegistroAtivo;

@Entity
@Table(name = "TB_TIPO_USUARIO", schema = "dbwebly")
public class TipoUsuario implements ExclusaoLogica {

	private static final long serialVersionUID = -2575122162242872106L;

	@Id
	@SequenceGenerator(name = "TB_TIPO_USUARIO_GENERATOR", sequenceName = "dbwebly.SQ_TIPOUSUARIO_COSEQTIPOUSUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_TIPO_USUARIO_GENERATOR")
	@Column(name = "CO_SEQ_TIPO_USUARIO", nullable = false, length = 9)
	private Long codigo;

	@Column(name = "DS_TIPO_USUARIO", nullable = false, length = 20)
	private String descricao;

	@Column(name = "ROLE_TIPO_USUARIO", nullable = false, length = 15)
	private String role;

	@Column(name = "ST_REGISTRO_ATIVO", nullable = false)
	private String statusRegistroAtivo;

	public TipoUsuario() {
		super();
	}

	public TipoUsuario(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatusRegistroAtivo() {
		return statusRegistroAtivo;
	}

	public void setStatusRegistroAtivo(String statusRegistroAtivo) {
		this.statusRegistroAtivo = statusRegistroAtivo;
	}

	@Override
	public Serializable getId() {
		return codigo;
	}

	@Override
	public void setExcluido(boolean excluir) {
		if (excluir) {
			setStatusRegistroAtivo(StatusRegistroAtivo.INATIVO.getValor());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoUsuario [codigo=" + codigo + ", descricao=" + descricao
				+ ", role=" + role + "]";
	}
}