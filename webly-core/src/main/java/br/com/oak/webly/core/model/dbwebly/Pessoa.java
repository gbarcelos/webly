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
@Table(name = "TB_PESSOA", schema = "dbwebly")
public class Pessoa implements ExclusaoLogica {

	private static final long serialVersionUID = 1178164319363495618L;

	@Id
	@SequenceGenerator(name = "TB_PESSOA_GENERATOR", sequenceName = "dbwebly.SQ_USUARIO_COSEQPESSOA", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_PESSOA_GENERATOR")
	@Column(name = "CO_SEQ_PESSOA", nullable = false, length = 9)
	private Long codigo;
	
	@Column(name = "NO_PESSOA", nullable = false, length = 100)
	private String nome;

	@Column(name = "EMAIL", nullable = false, length = 60)
	private String email;	

	@Column(name = "ST_REGISTRO_ATIVO", nullable = false)
	private String statusRegistroAtivo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [codigo=" + codigo + ", nome=" + nome + ", email="
				+ email + "]";
	}
}