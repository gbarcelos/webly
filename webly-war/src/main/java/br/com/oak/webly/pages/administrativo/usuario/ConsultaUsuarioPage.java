package br.com.oak.webly.pages.administrativo.usuario;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaConsultaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "consultaUsuarioPage")
public class ConsultaUsuarioPage extends PaginaConsultaPage {

	private static final long serialVersionUID = 4282150208904369922L;

	public ConsultaUsuarioPage() {
		super("usuario");

		add(new ConsultaUsuarioForm("formCOUSR", getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
	}
}