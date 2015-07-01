package br.com.oak.webly.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.vo.UsuarioLogado;
import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.webly.pages.publico.logout.LogoutPage;
import br.com.oak.webly.pages.publico.usuario.NovoUsuarioPage;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.menu.SimpleMenuItem;

public class SaudacaoPanel extends Panel {

	private static final long serialVersionUID = -6608184836768816408L;

	private PageHelper pageHelper;

	public SaudacaoPanel(final String idPanel, final PageHelper pageHelper) {

		super(idPanel);

		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {

		final UsuarioLogado usuarioLogado = AuthenticationUtils
				.getUsuarioLogado();

		criarLabelSaudacao(usuarioLogado);

		final List<SimpleMenuItem> lista = criarMenus(usuarioLogado);

		criarListViewMenu(lista);
	}

	private void criarLabelSaudacao(final UsuarioLogado usuarioLogado) {

		final StringBuilder saudacao = new StringBuilder();

		saudacao.append(pageHelper
				.getStringLabel("baseGeralPage.saudacaoPanel.titulo.label"));

		if (usuarioLogado != null) {
			saudacao.append(" ");
			saudacao.append(usuarioLogado.getNomePublico());
		}
		add(new Label("msgSaudacao", saudacao.toString()));
	}

	private List<SimpleMenuItem> criarMenus(final UsuarioLogado usuarioLogado) {

		final List<SimpleMenuItem> menus = new ArrayList<SimpleMenuItem>();

		SimpleMenuItem menuItem = null;

		if (usuarioLogado != null) {

			menuItem = new SimpleMenuItem();
			menuItem.setMenuText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.redefinirsenha.label"));
			menuItem.setMenuTitleText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.redefinirsenha.title"));
			menuItem.setResponsePageClass(HomePage.class);
			menus.add(menuItem);

			menuItem = new SimpleMenuItem();
			menuItem.setMenuText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.sair.label"));
			menuItem.setMenuTitleText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.sair.title"));
			menuItem.setResponsePageClass(LogoutPage.class);
			menus.add(menuItem);

		} else {

			menuItem = new SimpleMenuItem();
			menuItem.setMenuText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.novousuario.label"));
			menuItem.setMenuTitleText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.novousuario.title") + ParametrosCore.TITULO);
			menuItem.setResponsePageClass(NovoUsuarioPage.class);
			menus.add(menuItem);

			menuItem = new SimpleMenuItem();
			menuItem.setMenuText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.login.label"));
			menuItem.setMenuTitleText(pageHelper
					.getStringLabel("baseGeralPage.saudacaoPanel.link.login.title") + " " + ParametrosCore.TITULO);
			menuItem.setResponsePageClass(LoginPage.class);
			menus.add(menuItem);
		}
		return menus;
	}

	private void criarListViewMenu(final List<SimpleMenuItem> listaMenus) {

		add(new ListView<SimpleMenuItem>("listSaudacao", listaMenus) {

			private static final long serialVersionUID = -3506007494080188433L;

			@Override
			protected void populateItem(final ListItem<SimpleMenuItem> item) {

				final SimpleMenuItem menuItem = item.getModelObject();

				final AjaxLink<SimpleMenuItem> alink = new AjaxLink<SimpleMenuItem>(
						"label", item.getModel()) {

					private static final long serialVersionUID = -5546660316790153419L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						setResponsePage(menuItem.getResponsePageClass());
					}
				};
				alink.add(new Label("linklabel", menuItem.getMenuText()));
				alink.add(new AttributeModifier("title", menuItem
						.getMenuTitleText()));
				item.add(alink);
			}
		});
	}
}