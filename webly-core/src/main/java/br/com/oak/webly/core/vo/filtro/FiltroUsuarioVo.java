package br.com.oak.webly.core.vo.filtro;

import java.io.Serializable;

import br.com.oak.core.entidade.ParametroDeOrdenacao;
import br.com.oak.webly.core.enums.SimNaoEnum;
import br.com.oak.webly.core.enums.TipoUsuarioEnum;

public class FiltroUsuarioVo implements Serializable {

	private static final long serialVersionUID = -277573291367481658L;

	private String parteNome;
	
	private String nomeAutor;

	private String parteNomeAutor;

	private String email;

	private TipoUsuarioEnum tipoUsuario;

	private SimNaoEnum statusRegistroAtivo;

	private ParametroDeOrdenacao parametroDeOrdenacao;

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getParteNome() {
		return parteNome;
	}

	public void setParteNome(String parteNome) {
		this.parteNome = parteNome;
	}

	public String getParteNomeAutor() {
		return parteNomeAutor;
	}

	public void setParteNomeAutor(String parteNomeAutor) {
		this.parteNomeAutor = parteNomeAutor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public SimNaoEnum getStatusRegistroAtivo() {
		return statusRegistroAtivo;
	}

	public void setStatusRegistroAtivo(SimNaoEnum statusRegistroAtivo) {
		this.statusRegistroAtivo = statusRegistroAtivo;
	}

	public ParametroDeOrdenacao getParametroDeOrdenacao() {
		return parametroDeOrdenacao;
	}

	public void setParametroDeOrdenacao(
			ParametroDeOrdenacao parametroDeOrdenacao) {
		this.parametroDeOrdenacao = parametroDeOrdenacao;
	}
}