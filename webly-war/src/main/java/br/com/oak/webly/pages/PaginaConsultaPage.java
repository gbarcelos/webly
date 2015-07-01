package br.com.oak.webly.pages;

import br.com.oak.wicket.util.application.AcaoResponsePageEnum;

public class PaginaConsultaPage extends PaginaInternaPage {

	private static final long serialVersionUID = -380454277666110090L;

	public PaginaConsultaPage(final String dominio) {

		super(dominio, AcaoResponsePageEnum.CONSULTAR);
	}
}