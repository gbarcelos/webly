package br.com.oak.webly.pages.administrativo.conta;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.AlteraDadosPessoaisForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "adminAlteraDadosPessoaisPage")
public class AlteraDadosPessoaisPage extends PaginaExternaPage {

	private static final long serialVersionUID = 5446315407601160215L;

	public AlteraDadosPessoaisPage(final Long codigo) {

		super("alteraDadosPessoais");

		add(new AlteraDadosPessoaisForm("formAADP", codigo, getPageHelper()) {

			private static final long serialVersionUID = 5800489549595908133L;

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