package br.com.oak.webly.pages.publico.quemsomos;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.oak.webly.util.helper.PageHelper;

public class QuemSomosFormPanel extends Panel {

	private static final long serialVersionUID = 2408339275814041704L;

	public QuemSomosFormPanel(final String idPanel, final PageHelper pageHelper) {

		super(idPanel);

		add(new Label("p1", pageHelper.getPageStringLabel("texto1")));

		add(new Label("p2", pageHelper.getPageStringLabel("texto2")));

		add(new Label("p3", pageHelper.getPageStringLabel("texto3")));

		add(new Label("p4", pageHelper.getPageStringLabel("texto4")));
	}
}