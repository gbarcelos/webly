package br.com.oak.webly.pages.publico.quemsomos;

import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "quemSomosPage")
public class QuemSomosPage extends PaginaExternaPage {

	private static final long serialVersionUID = 8549988861639971323L;

	public QuemSomosPage() {

		super("quemSomos");

		setSubDescricaoTituloPagina(getPageHelper()
				.getSubDescricaoTituloPagina() + ParametrosCore.TITULO);

		add(new QuemSomosForm("formularioQuem", getPageHelper()));
	}
}