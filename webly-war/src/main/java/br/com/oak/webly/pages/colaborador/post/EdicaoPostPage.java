package br.com.oak.webly.pages.colaborador.post;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "edicaoPostPage")
public class EdicaoPostPage extends PaginaExternaPage {

	private static final long serialVersionUID = 6275092696890974356L;

	private static final String DOMINIO = "post";

	private static final String ID_FORM = "formEPOST";

	public EdicaoPostPage() {

		super(DOMINIO);

		add(new EdicaoPostForm(ID_FORM, getPageHelper()));
	}

	public EdicaoPostPage(final Long codigo) {

		super(DOMINIO);

		add(new EdicaoPostForm(ID_FORM, codigo, getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.renderJavaScriptReference("common/js/jquery.maxlength.js");

		response.renderJavaScriptReference("common/js/ckeditor/ckeditor.js");
		response.renderJavaScriptReference("common/js/ckeditor/adapters/jquery.js");
	}
}