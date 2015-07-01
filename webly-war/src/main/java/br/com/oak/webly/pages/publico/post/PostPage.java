package br.com.oak.webly.pages.publico.post;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.oak.webly.core.service.PostService;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.LinkPost;
import br.com.oak.webly.pages.publico.erro.ErroPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "postPage", parteUrl = "/post/${id}")
public class PostPage extends PaginaExternaPage {

	private static final long serialVersionUID = -2185862428839912875L;

	@SpringBean
	private PostService service;

	public PostPage(final PageParameters parameters) {

		final String url = parameters.get(LinkPost.PARAMETRO_GET_ID_POST)
				.toString();

		final PostVo post = service.obterPostByUrl(obterUrlPesquisa(url));

		if (post == null) {

			throw new RestartResponseException(ErroPage.class,
					new PageParameters().set(ConstantesWeb.PARAMETRO_GET_ERRO,
							"404"));
		}

		inicializar(post);
	}

	private String obterUrlPesquisa(final String url) {

		String urlPesquisa = null;

		if (StringUtils.isEmpty(ConstantesWeb.EXTENSAO_URL)) {
			urlPesquisa = url;

		} else {

			String[] partes = url.split(ConstantesWeb.EXTENSAO_URL);

			urlPesquisa = partes[0];
		}
		return urlPesquisa;
	}

	private void inicializar(final PostVo post) {
		popularTitulos(post);
		popularTextoPost(post);
		popularComentarios();
	}

	private void popularTitulos(final PostVo post) {

		setPageTitle(post.getTitulo());
		setDescricaoTituloPagina(post.getTitulo());

		popularSubDescricaoTituloPagina(post);

		addOrReplace(new Label("lblSubTitulo", post.getSubTitulo()));
	}

	private void popularTextoPost(final PostVo post) {

		final Label label = new Label("textoPost",
				StringEscapeUtils.unescapeHtml(post.getTextoPost()));

		label.setRenderBodyOnly(true);

		label.setEscapeModelStrings(false);

		add(label);
	}

	private void popularComentarios() {
		add(new ComentariosLazyLoadPanel("comentarios"));
	}

	private void popularSubDescricaoTituloPagina(final PostVo post) {

		final StringBuffer sb = new StringBuffer();

		sb.append(getPageHelper().getStringLabel(
				"postPage.subDescricaoTituloPagina.label1"));

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(post.getNomePublico());

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(getPageHelper().getStringLabel(
				"postPage.subDescricaoTituloPagina.label2"));

		sb.append(ConstantesCore.ESPACO_BRANCO);

		sb.append(post.getDataPublicacaoCompleta());

		setSubDescricaoTituloPagina(sb.toString());
	}
}