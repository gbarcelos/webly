package br.com.oak.webly.pages.visitante.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraUsuarioForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "visitAlteraUsuarioPage")
public class AlteraUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = 1621691459997936691L;

	public AlteraUsuarioPage(final Long codigo) {

		super("alteraUsuario");

		add(new AlteraUsuarioForm("formVAUSR", codigo, getPageHelper()){

			private static final long serialVersionUID = -370862218234529345L;

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