package br.com.oak.webly.pages.colaborador.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraDadosPessoaisForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "colabAlteraDadosPessoaisPage")
public class AlteraDadosPessoaisPage extends PaginaExternaPage {

	private static final long serialVersionUID = 1956258136466076516L;

	public AlteraDadosPessoaisPage(final Long codigo) {

		super("alteraDadosPessoais");

		add(new AlteraDadosPessoaisForm("formCADP", codigo, getPageHelper()) {

			private static final long serialVersionUID = 6229623139352157054L;

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