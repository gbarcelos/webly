package br.com.oak.webly.pages.publico.usuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;

import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "novoUsuarioPage", descricao = "Criar uma conta")
public class NovoUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = 2828664801378504109L;

	public NovoUsuarioPage() {

		super("novoUsuario");

		add(new Label("labelFrmNou", getPageStringLabel("titulo.texto1")));

		addLinkEntrar();

		add(new NovoUsuarioForm("frmNou", getPageHelper()));
	}

	private void addLinkEntrar() {

		add(new Label("labelOu", getPageStringLabel("titulo.texto2")));

		final AjaxLink<Object> linkCriarConta = new AjaxLink<Object>(
				"linkEntrar") {

			private static final long serialVersionUID = 3280839705446338796L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(LoginPage.class);
			}
		};

		linkCriarConta.add(new Label("labelLinkEntrar",
				getPageStringLabel("titulo.texto3.link")));

		linkCriarConta.add(new AttributeModifier("title",
				getPageStringLabel("titulo.texto3.title")
						+ ParametrosCore.TITULO));

		add(linkCriarConta);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("common/js/jquery.maxlength.js");
	}
}