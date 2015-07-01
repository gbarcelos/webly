package br.com.oak.webly.pages.publico.erro;

import org.apache.wicket.markup.html.basic.Label;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "acessoNegadoPage")
public class AcessoNegadoPage extends PaginaExternaPage {

	private static final long serialVersionUID = 3216147796709010233L;

	public AcessoNegadoPage() {

		super("acessoNegado");

		add(new Label("codigoErro", getPageStringLabel("texto1")));
		add(new Label("descricaoErro", getPageStringLabel("texto2")));
	}
}