package br.com.oak.webly.pages.administrativo.usuario;

import org.apache.wicket.markup.html.IHeaderResponse;

import br.com.oak.webly.pages.PaginaCadastroPage;
import br.com.oak.wicket.util.PaginaWeb;
import br.com.oak.wicket.util.application.AcaoResponsePageEnum;

@PaginaWeb(nome = "usuarioPage")
public class UsuarioPage extends PaginaCadastroPage {

	private static final long serialVersionUID = -121668539286410271L;

	public UsuarioPage(final Long codigo) {

		super("usuario", AcaoResponsePageEnum.ALTERAR);

		add(new UsuarioForm("formCAUSR", codigo, getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
	}
}