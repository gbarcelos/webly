package br.com.oak.webly.pages;

import org.apache.wicket.markup.html.basic.Label;

import br.com.oak.wicket.util.application.AcaoResponsePageEnum;

public class PaginaInternaPage extends BaseGeralPage {

	private static final long serialVersionUID = -6613790892128985880L;

	public PaginaInternaPage(final String dominio,
			final AcaoResponsePageEnum acao) {

		super(dominio);

		addOrReplace(new Label("tituloAcaoPagina", acao.getDescricao() + " "
				+ getPageHelper().getDominio()));
	}
}
