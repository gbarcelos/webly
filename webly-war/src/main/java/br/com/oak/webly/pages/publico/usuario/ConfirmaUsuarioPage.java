package br.com.oak.webly.pages.publico.usuario;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "confirmaUsuarioPage")
public class ConfirmaUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = 4713309901087509244L;

	public ConfirmaUsuarioPage(final PageParameters parameters) {

		super("confirmaUsuario");

		add(new ConfirmaUsuarioForm(
				"formularioConf",
				parameters.get(ConstantesCore.PARAMETRO_GET_USUARIO),
				parameters.get(ConstantesCore.PARAMETRO_GET_CODIGO_VERIFICACAO),
				getPageHelper()));
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
	}
}