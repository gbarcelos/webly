package br.com.oak.webly.menu;

import java.util.List;

import br.com.oak.webly.menu.builder.MenuBuilder;
import br.com.oak.wicket.ui.menu.MenuItem;

public class MenuDirector {

	protected MenuBuilder montadorMenu;

	public MenuDirector(MenuBuilder menuBuilder) {
		this.montadorMenu = menuBuilder;
	}

	public void construirMenuVisitante() {
		montadorMenu.buildInicio();
		montadorMenu.buildQuemSomos();
		montadorMenu.buildFaleConosco();
		montadorMenu.buildLoginLogout();
	}

	public void construirMenuPublico() {
		montadorMenu.buildInicio();
		montadorMenu.buildQuemSomos();
		montadorMenu.buildFaleConosco();
		montadorMenu.buildLoginLogout();
	}

	public void construirMenuColaborador() {
		montadorMenu.buildInicio();
		montadorMenu.buildPosts();
		montadorMenu.buildLoginLogout();
	}

	public void construirMenuAdministrativo() {
		montadorMenu.buildInicio();
		montadorMenu.buildPosts();
		montadorMenu.buildCadastros();
		montadorMenu.buildLoginLogout();
	}

	public List<MenuItem> getMenu() {
		return montadorMenu.getMenu();
	}
}