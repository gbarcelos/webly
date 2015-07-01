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
@Table(name = "TB_SITUACAO_USUARIO", schema = "dbwebly")
public class SituacaoUsuario implements ExclusaoLogica {

	private static final long serialVersionUID = 4958728309220115967L;

	@Id
	@SequenceGenerator(name = "TB_SITUACAO_USUARIO_GENERATOR", sequenceName = "dbwebly.SQ_COSEQSITUACAOUSUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_SITUACAO_USUARIO_GENERATOR")
	@Column(name = "CO_SEQ_SITUACAO_USUARIO", nullable = false, length = 9)
	private Long codigo;

	@Column(name = "DS_SITUACAO_USUARIO", nullable = false, length = 50)
	private String descricao;

	@Column(name = "ST_REGISTRO_ATIVO", nullable = false)
	private String statusRegistroAtivo;

	public SituacaoUsuario() {
	}

	public SituacaoUsuario(Long codigo) {
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
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		SituacaoUsuario other = (SituacaoUsuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SituacaoUsuario [codigo=" + codigo + ", descricao=" + descricao
				+ ", statusRegistroAtivo=" + statusRegistroAtivo + "]";
	}
}