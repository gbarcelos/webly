package br.com.oak.webly.menu.builder;

import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.publico.logout.LogoutPage;
import br.com.oak.webly.pages.visitante.conta.ConsultaContaUsuarioPage;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.wicket.ui.menu.MenuItem;

public class MenuVisitanteBuilder extends MenuBuilder {

	@Override
	public void buildCadastros() {
	}

	@Override
	public void buildLoginLogout() {

		final MenuItem menuItemLogado = new MenuItem(
				AuthenticationUtils.getNomeUsuarioLogado());
		
		//FIXME: Alterar para buscar labels do properties

		menuItemLogado.addSubmenu(new MenuItem("Informações sobre a conta",
				ConsultaContaUsuarioPage.class));

		menuItemLogado.addSubmenu(new MenuItem("Configurações pessoais",
				HomePage.class));

		menuItemLogado.addSubmenu(new MenuItem("Histórico de acessos",
				HomePage.class));

		menuItemLogado.addSubmenu(new MenuItem("Sair", LogoutPage.class));

		listaMenu.add(menuItemLogado);
	}

	@Override
	public void buildPosts() {
	}
}