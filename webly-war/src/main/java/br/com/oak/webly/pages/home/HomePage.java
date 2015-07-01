package br.com.oak.webly.pages.home;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.oak.webly.core.service.PostService;
import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.SessionKey;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "homePage", parteUrl = "/home", extensao = ConstantesWeb.EXTENSAO_URL)
public class HomePage extends PaginaExternaPage {

	private static final long serialVersionUID = 3447126518346379273L;

	@SpringBean
	private PostService service;

	public HomePage() {

		setOutputMarkupId(true);

		setDescricaoTituloPagina(ParametrosCore.DESCRICAO_TITULO_PAGINA_INICIAL);
		setSubDescricaoTituloPagina(StringUtils.EMPTY);

		criarPanelUltimas();
		criarListViewPosts();
	}

	@Override
	protected void criarPanelUltimas() {

		addOrReplace(new UltimosPostsPanel("panelUltimas") {

			private static final long serialVersionUID = -4078048924037503767L;

			@Override
			protected List<PostVo> obterLista() {

				final List<PostVo> lista = service.listarTopicosRecentes();

				Session.get().setAttribute(SessionKey.ULTIMOS_POSTS.getKey(),
						new UltimosPosts(lista));

				return lista;
			}
		});
	}

	private void criarListViewPosts() {
		addOrReplace(new GridHome("gridHome", new HomeProvider(service), getPageHelper()));
	}
}