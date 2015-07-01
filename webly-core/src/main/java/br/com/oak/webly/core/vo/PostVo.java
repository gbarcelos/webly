package br.com.oak.webly.core.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.oak.core.util.DataUtil;

public class PostVo implements Serializable {

	private static final long serialVersionUID = -6391931967213323598L;

	private Long codigo;

	private String titulo;

	private String subTitulo;

	private String nomePublico;

	private String dataPublicacao;

	private String dataPublicacaoCompleta;

	private String textoPost;

	private String parteUrl;

	private String textoResumoPost;

	public PostVo() {
	}

	public PostVo(final String titulo, final String subTitulo,
			final String nomePublico, final Date dataPublicacao,
			final String textoPost) {
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.nomePublico = nomePublico;
		this.dataPublicacao = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_HORA);
		this.dataPublicacaoCompleta = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_EXTENSO_FULL);

		this.textoPost = textoPost;
	}

	public PostVo(final String titulo, final String parteUrl) {
		this.titulo = titulo;
		this.parteUrl = parteUrl;
	}

	public PostVo(final Long codigo, final String titulo,
			final String nomePublico, final Date dataPublicacao) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.nomePublico = nomePublico;
		this.dataPublicacao = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_HORA);
		this.dataPublicacaoCompleta = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_EXTENSO_FULL);
	}

	public PostVo(final String titulo, final String parteUrl,
			final String nomePublico, final String textoResumoPost,
			final Date dataPublicacao) {
		this.titulo = titulo;
		this.parteUrl = parteUrl;
		this.nomePublico = nomePublico;
		this.textoResumoPost = textoResumoPost;
		this.dataPublicacao = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_HORA);
		this.dataPublicacaoCompleta = DataUtil.formataData(dataPublicacao,
				DataUtil.PATTERN_DATA_EXTENSO_FULL);
	}

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

	public String getNomePublico() {
		return nomePublico;
	}

	public void setNomePublico(String nomePublico) {
		this.nomePublico = nomePublico;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getDataPublicacaoCompleta() {
		return dataPublicacaoCompleta;
	}

	public void setDataPublicacaoCompleta(String dataPublicacaoCompleta) {
		this.dataPublicacaoCompleta = dataPublicacaoCompleta;
	}

	public String getTextoPost() {
		return textoPost;
	}

	public void setTextoPost(String textoPost) {
		this.textoPost = textoPost;
	}

	public String getParteUrl() {
		return parteUrl;
	}

	public void setParteUrl(String parteUrl) {
		this.parteUrl = parteUrl;
	}

	public String getTextoResumoPost() {
		return textoResumoPost;
	}

	public void setTextoResumoPost(String textoResumoPost) {
		this.textoResumoPost = textoResumoPost;
	}
}