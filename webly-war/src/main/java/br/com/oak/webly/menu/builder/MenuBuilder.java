package br.com.oak.webly.menu.builder;

import java.util.ArrayList;
import java.util.List;

import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.publico.faleconosco.FaleConoscoPage;
import br.com.oak.webly.pages.publico.quemsomos.QuemSomosPage;
import br.com.oak.wicket.ui.menu.MenuItem;

public abstract class MenuBuilder {

	protected List<MenuItem> listaMenu;

	public MenuBuilder() {
		listaMenu = new ArrayList<MenuItem>();
	}

	public void buildInicio() {
		//FIXME: Alterar para buscar labels do properties
		listaMenu.add(new MenuItem("Início", HomePage.class));
	}

	public void buildQuemSomos() {
		listaMenu.add(new MenuItem("Quem Somos", QuemSomosPage.class));
	}

	public void buildFaleConosco() {
		listaMenu.add(new MenuItem("Fale Conosco", FaleConoscoPage.class));
	}

	public abstract void buildPosts();	

	public abstract void buildCadastros();

	public abstract void buildLoginLogout();

	public List<MenuItem> getMenu() {
		return listaMenu;
	}
}