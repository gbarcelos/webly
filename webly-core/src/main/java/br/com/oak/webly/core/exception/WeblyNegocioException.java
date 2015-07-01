package br.com.oak.webly.core.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.oak.core.exception.Erro;

public class WeblyNegocioException extends RuntimeException {

	private static final long serialVersionUID = 2667966945242546294L;

	private List<Erro> erros;
	
	private Exception exceptionCausa;
	
	public WeblyNegocioException(){
		super();
	}
	
	public WeblyNegocioException(final Erro erro){
		super(erro.getCodigo());
		erros = new ArrayList<Erro>();
		erros.add(erro);
	}
	
	public WeblyNegocioException(final Erro erro, final Exception exceptionCausa){
		super(erro.getCodigo(), exceptionCausa);
		erros = new ArrayList<Erro>();
		erros.add(erro);
	}
	
	public WeblyNegocioException(final List<Erro> erros) {
		super();
		this.erros = erros;
	}
	
	public WeblyNegocioException(final List<Erro> erros, final Exception exceptionCausa){
		this(erros);
		this.exceptionCausa = exceptionCausa;
	}

	public List<Erro> getErros() {
		return erros;
	}

	public void setMensagens(final List<Erro> erros) {
		this.erros = erros;
	}

	public Exception getExceptionCausa() {
		return exceptionCausa;
	}

	public void setExceptionCausa(final Exception exceptionCausa) {
		this.exceptionCausa = exceptionCausa;
	}
}