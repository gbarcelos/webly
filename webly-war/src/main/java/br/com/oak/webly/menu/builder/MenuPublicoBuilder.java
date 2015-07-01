package br.com.oak.webly.menu.builder;

import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.wicket.ui.menu.MenuItem;


public class MenuPublicoBuilder extends MenuBuilder {

	@Override
	public void buildCadastros() {
	}

	@Override
	public void buildLoginLogout() {
		//FIXME: Alterar para buscar labels do properties
		listaMenu.add(new MenuItem("Entrar", LoginPage.class));
	}

	@Override
	public void buildPosts() {
	}
}