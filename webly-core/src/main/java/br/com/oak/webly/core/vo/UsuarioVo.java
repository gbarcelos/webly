package br.com.oak.webly.core.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.oak.core.util.DataUtil;
import br.com.oak.webly.core.enums.SimNaoEnum;

public class UsuarioVo implements Serializable {

	private static final long serialVersionUID = 6719544531543641386L;

	private Long codigo;

	private String nome;

	private String nomePublico;

	private String roleUsuario;

	private Long codigoPessoa;

	private String nomePessoa;

	private String email;

	private String descricaoTipoUsuario;

	private String dataInclusao;

	private String dataAlteracao;

	private String status;

	public UsuarioVo(final Long codigo, final String nomePessoa,
			final String nome, final String email,
			final String descricaoTipoUsuario, final Date dataInclusao,
			final Date dataAlteracao, final SimNaoEnum statusRegistroAtivo) {

		this.codigo = codigo;
		this.nomePessoa = nomePessoa;
		this.nome = nome;
		this.email = email;
		this.descricaoTipoUsuario = descricaoTipoUsuario;

		this.dataInclusao = DataUtil.formataData(dataInclusao,
				DataUtil.PATTERN_DATA_HORA);

		this.dataAlteracao = DataUtil.formataData(dataAlteracao,
				DataUtil.PATTERN_DATA_HORA);

		if (SimNaoEnum.SIM.equals(statusRegistroAtivo)) {
			this.status = "Ativo";

		} else if (SimNaoEnum.NAO.equals(statusRegistroAtivo)) {
			this.status = "Inativo";
		}
	}

	public UsuarioVo(final Long codigo, final Long codigoPessoa,
			final String nomePessoa, final String nome,
			final String nomePublico, final String email,
			final String roleUsuario, final Date dataInclusao) {

		this.codigo = codigo;
		this.codigoPessoa = codigoPessoa;
		this.nomePessoa = nomePessoa;
		this.nome = nome;
		this.nomePublico = nomePublico;
		this.email = email;
		this.roleUsuario = roleUsuario;
		this.dataInclusao = DataUtil.formataData(dataInclusao,
				DataUtil.PATTERN_DATA_EXTENSO);
	}

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

	public String getNomePublico() {
		return nomePublico;
	}

	public void setNomePublico(String nomePublico) {
		this.nomePublico = nomePublico;
	}

	public String getRoleUsuario() {
		return roleUsuario;
	}

	public void setRoleUsuario(String roleUsuario) {
		this.roleUsuario = roleUsuario;
	}

	public Long getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(Long codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricaoTipoUsuario() {
		return descricaoTipoUsuario;
	}

	public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
		this.descricaoTipoUsuario = descricaoTipoUsuario;
	}

	public String getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(String dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return nome;
	}
}