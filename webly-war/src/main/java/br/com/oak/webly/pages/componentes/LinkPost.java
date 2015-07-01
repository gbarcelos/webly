package br.com.oak.webly.pages.componentes;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.pages.publico.post.PostPage;
import br.com.oak.webly.util.ConstantesWeb;

public class LinkPost<T> extends BookmarkablePageLink<T> {

	private static final long serialVersionUID = 8603102030913432629L;

	public static final String PARAMETRO_GET_ID_POST = "id";

	public <C extends Page> LinkPost(final String idLink, final PostVo post) {

		super(idLink, PostPage.class, null);

		final PageParameters parametros = new PageParameters();
		parametros.add(PARAMETRO_GET_ID_POST, post.getParteUrl()
				+ ConstantesWeb.EXTENSAO_URL);

		this.parameters = parametros;
	}
}