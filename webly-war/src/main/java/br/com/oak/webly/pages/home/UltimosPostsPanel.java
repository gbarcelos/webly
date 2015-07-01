package br.com.oak.webly.pages.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.springframework.util.CollectionUtils;

import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.pages.componentes.LinkPost;
import br.com.oak.wicket.ui.menu.SimpleMenuItem;

public abstract class UltimosPostsPanel extends Panel {

	private static final long serialVersionUID = -2546686820139350421L;

	public UltimosPostsPanel(final String idPanel) {
		super(idPanel);

		inicializar();
	}

	private void inicializar() {

		add(new Label("msgTopico", "Tópicos recentes"));
		//FIXME: Alterar para buscar labels do properties

		criarListViewMenu(criarMenus(obterLista()));
	}

	protected abstract List<PostVo> obterLista();

	private List<SimpleMenuItem> criarMenus(final List<PostVo> lista) {

		List<SimpleMenuItem> menus = null;

		if (!CollectionUtils.isEmpty(lista)) {

			menus = new ArrayList<SimpleMenuItem>(lista.size());
			SimpleMenuItem menuItem;

			for (final PostVo post : lista) {

				final Link<Void> postLink = new LinkPost<Void>("linkPost", post);

				postLink.add(new Label("linkPostlabel", post.getTitulo()));

				menuItem = new SimpleMenuItem();
				menuItem.setInternalLink(postLink);

				menus.add(menuItem);
			}
		}
		return menus;
	}

	private void criarListViewMenu(List<SimpleMenuItem> itens) {

		add(new ListView<SimpleMenuItem>("listUltimos", itens) {

			private static final long serialVersionUID = 3777245683547903666L;

			@Override
			protected void populateItem(final ListItem<SimpleMenuItem> item) {

				item.add(item.getModelObject().getInternalLink());
			}
		});
	}
}