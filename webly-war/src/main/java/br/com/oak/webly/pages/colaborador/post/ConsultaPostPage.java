package br.com.oak.webly.pages.colaborador.post;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaConsultaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "consultaPostPage")
public class ConsultaPostPage extends PaginaConsultaPage {

	private static final long serialVersionUID = -5922317009388426561L;

	public ConsultaPostPage() {

		super("post");

		add(new ConsultaPostForm("formCPOST", getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
		response.renderJavaScriptReference("common/js/mascara.js");
	}
}