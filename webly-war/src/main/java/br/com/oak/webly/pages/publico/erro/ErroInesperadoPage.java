package br.com.oak.webly.pages.publico.erro;

import org.apache.wicket.markup.html.basic.Label;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "erroInesperadoPage", parteUrl = "/publico/erro-inesperado", extensao = ConstantesWeb.EXTENSAO_URL)
public class ErroInesperadoPage extends PaginaExternaPage {

	private static final long serialVersionUID = 7250609607104599454L;

	public ErroInesperadoPage() {

		super("erroInesperado");

		add(new Label("codigoErro", getPageStringLabel("texto1")));
		add(new Label("descricaoErro", getPageStringLabel("texto2")));
	}
}