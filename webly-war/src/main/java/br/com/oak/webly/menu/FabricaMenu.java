package br.com.oak.webly.menu;

import java.util.List;

import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.menu.builder.MenuAdministrativoBuilder;
import br.com.oak.webly.menu.builder.MenuColaboradorBuilder;
import br.com.oak.webly.menu.builder.MenuPublicoBuilder;
import br.com.oak.webly.menu.builder.MenuVisitanteBuilder;
import br.com.oak.wicket.ui.menu.MenuItem;

public class FabricaMenu {

	public List<MenuItem> criarMenuPorTipoDeUsuario(
			final TipoUsuarioEnum perfilUsuario) {

		if (perfilUsuario != null) {

			switch (perfilUsuario) {
			case VISITANTE:
				return getMenuVisitante();
				
			case COLABORADOR:
				return getMenuColaborador();			

			case SUPER_ADMINISTRATIVO:
			case ADMINISTRATIVO:
				return getMenuAdministrativo();

			default:
				return getMenuPublico();
			}

		} else {
			return getMenuPublico();
		}
	}

	public List<MenuItem> criarMenuPublico() {
		return getMenuPublico();
	}

	private List<MenuItem> getMenuColaborador() {
		final MenuDirector menuDirector = new MenuDirector(
				new MenuColaboradorBuilder());
		menuDirector.construirMenuColaborador();

		return menuDirector.getMenu();
	}

	private List<MenuItem> getMenuAdministrativo() {
		final MenuDirector menuDirector = new MenuDirector(
				new MenuAdministrativoBuilder());
		menuDirector.construirMenuAdministrativo();

		return menuDirector.getMenu();
	}

	private List<MenuItem> getMenuPublico() {
		final MenuDirector menuDirector = new MenuDirector(
				new MenuPublicoBuilder());
		menuDirector.construirMenuPublico();

		return menuDirector.getMenu();
	}

	private List<MenuItem> getMenuVisitante() {
		final MenuDirector menuDirector = new MenuDirector(
				new MenuVisitanteBuilder());
		menuDirector.construirMenuVisitante();

		return menuDirector.getMenu();
	}
}