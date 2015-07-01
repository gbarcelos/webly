package br.com.oak.webly.menu.builder;

import br.com.oak.webly.pages.colaborador.post.ConsultaPostPage;
import br.com.oak.webly.pages.colaborador.post.EdicaoPostPage;
import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.wicket.ui.menu.MenuItem;

public abstract class MenuComunAdmColabBuilder extends MenuBuilder {

	@Override
	public void buildPosts() {
		final MenuItem menuItemPost = new MenuItem("Posts");
		//FIXME: Alterar para buscar labels do properties

		menuItemPost.addSubmenu(new MenuItem("Todos os Posts", ConsultaPostPage.class));
		menuItemPost.addSubmenu(new MenuItem("Adicionar Novo", EdicaoPostPage.class));
		menuItemPost.addSubmenu(new MenuItem("Categorias", HomePage.class));
		menuItemPost.addSubmenu(new MenuItem("Tags", HomePage.class));

		listaMenu.add(menuItemPost);
	}
}