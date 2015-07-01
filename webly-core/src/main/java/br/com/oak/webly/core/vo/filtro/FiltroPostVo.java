package br.com.oak.webly.core.vo.filtro;

import java.io.Serializable;
import java.util.Date;

import br.com.oak.webly.core.vo.UsuarioVo;

public class FiltroPostVo implements Serializable {

	private static final long serialVersionUID = 6568643890214294186L;

	private String url;

	private String parteTitulo;

	private Date dateIniPublicacao;

	private Date dateFimPublicacao;
	
	private UsuarioVo autor;

	public FiltroPostVo() {
	}

	public FiltroPostVo(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParteTitulo() {
		return parteTitulo;
	}

	public void setParteTitulo(String parteTitulo) {
		this.parteTitulo = parteTitulo;
	}

	public Date getDateIniPublicacao() {
		return dateIniPublicacao;
	}

	public void setDateIniPublicacao(Date dateIniPublicacao) {
		this.dateIniPublicacao = dateIniPublicacao;
	}

	public Date getDateFimPublicacao() {
		return dateFimPublicacao;
	}

	public void setDateFimPublicacao(Date dateFimPublicacao) {
		this.dateFimPublicacao = dateFimPublicacao;
	}

	public UsuarioVo getAutor() {
		return autor;
	}

	public void setAutor(UsuarioVo autor) {
		this.autor = autor;
	}
}