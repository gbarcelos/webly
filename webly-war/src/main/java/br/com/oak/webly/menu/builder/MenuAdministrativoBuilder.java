package br.com.oak.webly.menu.builder;

import br.com.oak.webly.pages.administrativo.conta.ConsultaContaUsuarioPage;
import br.com.oak.webly.pages.administrativo.usuario.ConsultaUsuarioPage;
import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.publico.logout.LogoutPage;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.wicket.ui.menu.MenuItem;

public class MenuAdministrativoBuilder extends MenuComunAdmColabBuilder {

	@Override
	public void buildCadastros() {
		
		//FIXME: Alterar para buscar labels do properties

		final MenuItem menuItemTabela = new MenuItem("Cadastros");

		menuItemTabela.addSubmenu(new MenuItem("Quem Somos", HomePage.class));

		menuItemTabela.addSubmenu(new MenuItem("Usuários",
				ConsultaUsuarioPage.class));

		menuItemTabela.addSubmenu(new MenuItem("Logs", HomePage.class));

		listaMenu.add(menuItemTabela);
	}

	@Override
	public void buildLoginLogout() {

		final MenuItem menuItemLogado = new MenuItem(
				AuthenticationUtils.getNomeUsuarioLogado());

		menuItemLogado.addSubmenu(new MenuItem("Informações sobre a conta",
				ConsultaContaUsuarioPage.class));

		menuItemLogado.addSubmenu(new MenuItem("Configurações pessoais",
				HomePage.class));

		menuItemLogado.addSubmenu(new MenuItem("Histórico de acessos",
				HomePage.class));

		menuItemLogado.addSubmenu(new MenuItem("Sair", LogoutPage.class));

		listaMenu.add(menuItemLogado);
	}
}