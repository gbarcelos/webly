package br.com.oak.webly.pages.administrativo.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraUsuarioForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "adminAlteraUsuarioPage")
public class AlteraUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = 7331301434643392823L;

	public AlteraUsuarioPage(final Long codigo) {

		super("alteraUsuario");

		add(new AlteraUsuarioForm("formAAUSR", codigo, getPageHelper()) {

			private static final long serialVersionUID = -3360512179534528794L;

			@Override
			protected void voltar() {
				setResponsePage(ConsultaContaUsuarioPage.class);
			}
		});
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
	}
}