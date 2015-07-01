package br.com.oak.webly.pages.colaborador.post;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;

import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.wicket.ui.grid.paginacao.PaginacaoTexto;

public abstract class GridPost extends Panel {

	private static final long serialVersionUID = 1562756470417855107L;

	private PostProvider provider;

	private DataView<PostVo> dataView;

	public GridPost(final String idPanel, final PostProvider provider) {

		super(idPanel);

		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);

		this.provider = provider;

		criarLista();
		setVisible(false);
	}

	private void criarLista() {

		dataView = new DataView<PostVo>("dataViewPost", provider) {

			private static final long serialVersionUID = -2822678305376756802L;

			@Override
			protected void populateItem(final Item<PostVo> item) {

				final PostVo post = item.getModelObject();

				item.add(new Label("titulo", post.getTitulo()));
				item.add(new Label("autor", post.getNomePublico()));
				item.add(new Label("dataPublicacao", post.getDataPublicacao()));

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
		add(new PaginacaoTexto("navigatorPost", dataView, provider,
				GridPost.this));
	}

	private Link<PostVo> criarLinkAlterar(final Item<PostVo> item) {
		final Link<PostVo> linkAlterar = new Link<PostVo>("alterar",
				item.getModel()) {

			private static final long serialVersionUID = -6454941294260882350L;

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

	protected abstract void comandoAlterar(PostVo post);
}