package br.com.oak.webly.core.model.dbwebly;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.oak.core.entidade.ExclusaoLogica;
import br.com.oak.core.enums.StatusRegistroAtivo;

@Entity
@Table(name = "TB_POST", schema = "dbwebly")
public class Post implements ExclusaoLogica {

	private static final long serialVersionUID = -6307551539914455809L;

	@Id
	@SequenceGenerator(name = "TB_POST_GENERATOR", sequenceName = "dbwebly.SQ_POST", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_POST_GENERATOR")
	@Column(name = "CO_SEQ_POST", nullable = false, length = 9)
	private Long codigo;

	@Column(name = "DS_TITULO", length = 45, nullable = false)
	private String titulo;

	@Column(name = "DS_SUBTITULO", length = 100)
	private String subTitulo;

	@Column(name = "PARTE_URL", length = 45, nullable = false)
	private String parteUrl;

	@Column(name = "DS_POST", nullable = false)
	private String textoPost;
	
	@Column(name = "DS_RESUMO_POST")
	private String textoResumoPost;

	@ManyToOne
	@JoinColumn(name = "CO_USUARIO", nullable = false)
	private Usuario usuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO", nullable = false)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_PUBLICACAO")
	private Date dataPublicacao;

	@Column(name = "ST_REGISTRO_ATIVO", nullable = false)
	private String statusRegistroAtivo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getParteUrl() {
		return parteUrl;
	}

	public void setParteUrl(String parteUrl) {
		this.parteUrl = parteUrl;
	}

	public String getTextoPost() {
		return textoPost;
	}

	public void setTextoPost(String textoPost) {
		this.textoPost = textoPost;
	}

	public String getTextoResumoPost() {
		return textoResumoPost;
	}

	public void setTextoResumoPost(String textoResumoPost) {
		this.textoResumoPost = textoResumoPost;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
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
		Post other = (Post) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}