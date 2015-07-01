package br.com.oak.webly.pages.colaborador.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraUsuarioForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "colabAlteraUsuarioPage")
public class AlteraUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = -8796497127960713153L;

	public AlteraUsuarioPage(final Long codigo) {

		super("alteraUsuario");

		add(new AlteraUsuarioForm("formCAUSR", codigo, getPageHelper()) {

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