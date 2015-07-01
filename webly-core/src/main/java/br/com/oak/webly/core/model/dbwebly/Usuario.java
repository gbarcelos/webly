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
import javax.persistence.Transient;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import br.com.oak.core.entidade.ExclusaoLogica;
import br.com.oak.webly.core.enums.SimNaoEnum;

@Entity
@Table(name = "TB_USUARIO", schema = "dbwebly")
public class Usuario implements ExclusaoLogica {

	private static final long serialVersionUID = 7584044927718969515L;

	@Id
	@SequenceGenerator(name = "TB_USUARIO_GENERATOR", sequenceName = "dbwebly.SQ_USUARIO_COSEQUSUARIO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_USUARIO_GENERATOR")
	@Column(name = "CO_SEQ_USUARIO", nullable = false, length = 9)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "CO_PESSOA")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "CO_TIPO_USUARIO")
	private TipoUsuario tipoUsuario;

	@Column(name = "PASS", length = 50, nullable = false)
	private String senha;

	@Column(name = "NOME", length = 30, nullable = false)
	private String nome;

	@Column(name = "NOME_PUBLICO", length = 30, nullable = false)
	private String nomePublico;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INCLUSAO", nullable = false)
	private Date dataInclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ALTERACAO")
	private Date dataAlteracao;

	@ManyToOne
	@JoinColumn(name = "CO_SITUACAO_USUARIO")
	private SituacaoUsuario situacaoUsuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CONFIRMACAO")
	private Date dataConfirmacao;

	@Column(name = "CODIGO_VERIFICACAO", length = 10, nullable = false)
	private String codigoVerificacao;

	@Type(type = "br.com.oak.core.model.CodeDataEnumUserType", parameters = { @Parameter(name = "enumClass", value = "br.com.oak.webly.core.enums.SimNaoEnum") })
	@Column(name = "ST_REGISTRO_ATIVO", nullable = false)
	private SimNaoEnum statusRegistroAtivo;

	@Transient
	private String novaSenha;

	@Transient
	private String confirmaNovaSenha;

	public Usuario() {
	}

	public Usuario(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SimNaoEnum getStatusRegistroAtivo() {
		return statusRegistroAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomePublico() {
		return nomePublico;
	}

	public void setNomePublico(String nomePublico) {
		this.nomePublico = nomePublico;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public SituacaoUsuario getSituacaoUsuario() {
		return situacaoUsuario;
	}

	public void setSituacaoUsuario(SituacaoUsuario situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}

	public Date getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(Date dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public String getCodigoVerificacao() {
		return codigoVerificacao;
	}

	public void setCodigoVerificacao(String codigoVerificacao) {
		this.codigoVerificacao = codigoVerificacao;
	}

	public void setStatusRegistroAtivo(SimNaoEnum statusRegistroAtivo) {
		this.statusRegistroAtivo = statusRegistroAtivo;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}

	@Override
	public Serializable getId() {
		return codigo;
	}

	@Override
	public void setExcluido(boolean excluir) {
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", pessoa=" + pessoa
				+ ", tipoUsuario=" + tipoUsuario + ", nome=" + nome + "]";
	}
}