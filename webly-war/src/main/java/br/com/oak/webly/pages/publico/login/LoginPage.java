package br.com.oak.webly.pages.publico.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;

import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.publico.usuario.NovoUsuarioPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "loginPage", parteUrl = "/login", extensao = ConstantesWeb.EXTENSAO_URL)
public class LoginPage extends PaginaExternaPage {

	private static final long serialVersionUID = -5472337917073039187L;

	public LoginPage() {

		super("login");

		add(new Label("labelFrmLg", getPageStringLabel("titulo.texto1")));

		addLinkCriarConta();

		add(new LoginForm("frmLg", getPageHelper()));
	}

	private void addLinkCriarConta() {

		add(new Label("labelOu", getPageStringLabel("titulo.texto2")));

		final AjaxLink<Object> linkCriarConta = new AjaxLink<Object>(
				"linkCriarConta") {

			private static final long serialVersionUID = 3280839705446338796L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(NovoUsuarioPage.class);
			}
		};

		linkCriarConta.add(new Label("labelLinkCriarConta",
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