package br.com.oak.webly.pages.visitante.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraDadosPessoaisForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "visitAlteraDadosPessoaisPage")
public class AlteraDadosPessoaisPage extends PaginaExternaPage {

	private static final long serialVersionUID = -6470961067258834024L;

	public AlteraDadosPessoaisPage(final Long codigo) {

		super("alteraDadosPessoais");

		add(new AlteraDadosPessoaisForm("formVADP", codigo, getPageHelper()) {

			private static final long serialVersionUID = 1021499281518797215L;

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