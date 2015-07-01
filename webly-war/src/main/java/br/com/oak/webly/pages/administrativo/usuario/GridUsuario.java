package br.com.oak.webly.pages.administrativo.usuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;

import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.wicket.ui.grid.paginacao.PaginacaoTexto;

public abstract class GridUsuario extends Panel {

	private static final long serialVersionUID = -7050460592983666479L;

	private UsuarioProvider provider;

	private DataView<UsuarioVo> dataView;

	public GridUsuario(final String idPanel, final UsuarioProvider provider) {

		super(idPanel);

		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);

		this.provider = provider;

		criarLista();
		setVisible(false);
	}

	private void criarLista() {

		dataView = new DataView<UsuarioVo>("dataViewUsuario", provider) {

			private static final long serialVersionUID = -7753098427526912649L;

			@Override
			protected void populateItem(final Item<UsuarioVo> item) {

				final UsuarioVo usuario = item.getModelObject();

				item.add(new Label("nomePessoa", usuario.getNomePessoa()));
				item.add(new Label("nome", usuario.getNome()));
				item.add(new Label("email", usuario.getEmail()));
				item.add(new Label("descricaoTipoUsuario", usuario
						.getDescricaoTipoUsuario()));
				item.add(new Label("dataInclusao", usuario.getDataInclusao()));

				final WebMarkupContainer acoes = new WebMarkupContainer("acoes");
				acoes.add(new AttributeModifier("headers", "acoes"));

				acoes.add(criarLinkAlterar(item));
				acoes.setVisible(true);
				acoes.setOutputMarkupId(true);
				item.add(acoes);

				item.add(AttributeModifier.replace("class",
						new AbstractReadOnlyModel<String>() {

							private static final long serialVersionUID = 8981987908136208425L;

							@Override
							public String getObject() {
								return (item.getIndex() % 2 == 1) ? "even"
										: "odd";
							}
						}));
			}
		};
		dataView.setItemsPerPage(provider.getItensPorPagina());
		dataView.setOutputMarkupId(true);
		add(dataView);

		add(new PaginacaoTexto("navigatorUsuario", dataView, provider,
				GridUsuario.this));
	}

	private Link<UsuarioVo> criarLinkAlterar(final Item<UsuarioVo> item) {
		final Link<UsuarioVo> linkAlterar = new Link<UsuarioVo>("alterar",
				item.getModel()) {

			private static final long serialVersionUID = -2361695716452369116L;

			@Override
			public void onClick() {
				comandoAlterar(getModelObject());
			}
		};
		linkAlterar.setVisible(true);
		return linkAlterar;
	}

	public void exibirLista(boolean visualizar) {
		dataView.setVisible(visualizar);
		setVisible(visualizar);
	}

	protected abstract void comandoAlterar(UsuarioVo usuario);
}