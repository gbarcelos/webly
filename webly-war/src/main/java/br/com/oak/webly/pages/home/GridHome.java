package br.com.oak.webly.pages.home;

import java.util.Random;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.pages.componentes.LinkPost;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.grid.paginacao.PaginacaoTexto;

public class GridHome extends Panel {

	private static final long serialVersionUID = -4463321963871878845L;

	private String[] patImagens;

	private final HomeProvider provider;

	private final PageHelper pageHelper;

	public GridHome(final String idPanel, final HomeProvider provider,
			final PageHelper pageHelper) {

		super(idPanel);
		setOutputMarkupId(true);
		setOutputMarkupPlaceholderTag(true);

		this.provider = provider;
		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {

		patImagens = new String[] { "image-2-270777_200x200.png",
				"image-5-290559_200x200.png", "photo-3-602019_200x200.png",
				"photo-8-743872_200x200.png" };

		final DataView<PostVo> dataView = new DataView<PostVo>("posts",
				provider) {

			private static final long serialVersionUID = -2822678305376756802L;

			@Override
			protected void populateItem(final Item<PostVo> item) {

				final PostVo post = item.getModelObject();

				popularLinkTitulo(item, post);
				popularPostadoPor(item, post);
				popularImagem(item, post);
				popularResumoPost(item, post);
				popularLinkLeiaMais(item, post);
			}
		};
		dataView.setItemsPerPage(provider.getItensPorPagina());
		dataView.setOutputMarkupId(true);
		add(dataView);

		add(new PaginacaoTexto("navigatorPost", dataView, provider,
				GridHome.this));
	}

	private void popularLinkTitulo(final ListItem<PostVo> item,
			final PostVo post) {

		final Link<Void> postLinkTitulo = new LinkPost<Void>("linkTitulo", post);

		postLinkTitulo.add(new Label("linkTituloLabel", post.getTitulo()));

		item.add(postLinkTitulo);
	}

	private void popularPostadoPor(final ListItem<PostVo> item,
			final PostVo post) {

		final StringBuffer sb = new StringBuffer();

		sb.append(pageHelper
				.getStringLabel("postPage.subDescricaoTituloPagina.label1"));

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(post.getNomePublico());

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(pageHelper
				.getStringLabel("postPage.subDescricaoTituloPagina.label2"));

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(post.getDataPublicacaoCompleta());

		item.add(new Label("postadoPor", sb.toString()));
	}

	private void popularImagem(final Item<PostVo> item, final PostVo post) {

		final Link<Void> postLink = new LinkPost<Void>("linkImgPost", post);

		postLink.add(criarImagemDestaquePost(post));

		postLink.add(new AttributeModifier("title", "Detalhar Post"));
		//FIXME: Alterar para buscar labels do properties

		item.add(postLink);
	}

	private ContextImage criarImagemDestaquePost(final PostVo post) {

		final ContextImage imagem = new ContextImage("imgPost",
				obterPathImagem());

		final StringBuffer alt = new StringBuffer("Detalhar post ");
		alt.append(post.getTitulo());
		//FIXME: Alterar para buscar labels do properties

		imagem.add(new AttributeModifier("alt", alt.toString()));

		return imagem;
	}

	/**
	 * PROVISÓRIO - será substituido pela imagem configurada para cada post.
	 * 
	 * @return
	 */
	private String obterPathImagem() {

		final Random random = new Random();

		final StringBuilder path = new StringBuilder("common/images/");
		path.append(patImagens[random.nextInt(4)]);

		return path.toString();
	}

	private void popularResumoPost(final ListItem<PostVo> item,
			final PostVo post) {
		item.add(new Label("resumoPost", post.getTextoResumoPost() + " [...]"));
	}

	private void popularLinkLeiaMais(final ListItem<PostVo> item,
			final PostVo post) {

		final Link<Void> postLinkLeiaMais = new LinkPost<Void>("linkLeiaMais",
				post);

		postLinkLeiaMais.add(new Label("linkLeiaMaislabel", "Leia mais"));
		//FIXME: Alterar para buscar labels do properties

		item.add(postLinkLeiaMais);
	}
}