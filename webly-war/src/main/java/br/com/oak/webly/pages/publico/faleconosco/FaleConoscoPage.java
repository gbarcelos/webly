package br.com.oak.webly.pages.publico.faleconosco;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "faleConoscoPage")
public class FaleConoscoPage extends PaginaExternaPage {

	private static final long serialVersionUID = -2804307692699643668L;

	public FaleConoscoPage() {

		super("faleConosco");

		add(new FaleConoscoForm("formularioFale", getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
//		response.renderJavaScriptReference("common/js/mascara.js");
	}
}