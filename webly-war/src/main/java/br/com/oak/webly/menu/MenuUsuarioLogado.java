package br.com.oak.webly.menu;

import java.io.Serializable;
import java.util.List;

import br.com.oak.wicket.ui.menu.MenuItem;

public class MenuUsuarioLogado implements Serializable{

	private static final long serialVersionUID = -4727803758168455142L;

	private List<MenuItem> menus;

	public MenuUsuarioLogado(List<MenuItem> menus){
		this.menus = menus;
	}

	public List<MenuItem> getMenus() {
		return menus;
	}
}