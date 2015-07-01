package br.com.oak.webly.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.springframework.util.CollectionUtils;

import br.com.oak.core.util.DataUtil;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.menu.FabricaMenu;
import br.com.oak.webly.menu.MenuUsuarioLogado;
import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.home.UltimosPosts;
import br.com.oak.webly.pages.home.UltimosPostsPanel;
import br.com.oak.webly.pages.publico.faleconosco.FaleConoscoPage;
import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.webly.pages.publico.quemsomos.QuemSomosPage;
import br.com.oak.webly.util.SessionKey;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.menu.Menu;
import br.com.oak.wicket.ui.menu.MenuItem;

public class BaseGeralPage extends WebPage {

	private static final long serialVersionUID = 37005035028994740L;

	private static final String ID_LINK_IMG = "linkImg";

	private static final String ID_IMG = "img";

	private static final String ID_MENU = "menuW";

	private String pageTitle;

	private PageHelper pageHelper;

	public BaseGeralPage() {

		pageTitle = null;
		pageHelper = new PageHelper();

		add(new Label("pageSlogan", ParametrosCore.SUB_TITULO));
		add(new Label(ID_LINK_IMG));
		add(new Label(ID_IMG));
		add(new Label(ID_MENU));

		criarBarraLateral();
		criarRodapeLinks();
	}

	/**
	 * Construtor responsável por inicializar o pageHelper e configurar títulos
	 * da página.
	 * 
	 * @param dominio
	 */
	public BaseGeralPage(final String dominio) {

		this();

		inicializarTitulos(dominio);
	}

	private void criarBarraLateral() {
		criarPanelSaudacao();
		criarPanelUltimas();
	}

	private void criarRodapeLinks() {
		addLinkInicio();
		addLinkFaleConosco();
		addLinkLogin();
		addLinkQuemSomos();
		addLinkExternoWicket();
	}

	private void criarPanelSaudacao() {
		addOrReplace(new SaudacaoPanel("panelSaudacao", pageHelper));
	}

	protected void criarPanelUltimas() {

		addOrReplace(new UltimosPostsPanel("panelUltimas") {

			private static final long serialVersionUID = -4477925132489546100L;

			@Override
			protected List<PostVo> obterLista() {

				final UltimosPosts ultimosPosts = (UltimosPosts) Session.get()
						.getAttribute(SessionKey.ULTIMOS_POSTS.getKey());

				if (ultimosPosts != null
						&& !CollectionUtils.isEmpty(ultimosPosts.getPosts())) {
					return ultimosPosts.getPosts();
				} else {
					return new ArrayList<PostVo>();
				}
			}
		});
	}

	private void addLinkInicio() {

		final AjaxLink<Object> linkInicio = new AjaxLink<Object>("linkInicio") {

			private static final long serialVersionUID = -3393741149561673195L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(HomePage.class);
			}

		};
		linkInicio.add(new Label("labelLinkInicio", pageHelper
				.getStringLabel("baseGeralPage.link.home.label")));

		linkInicio.add(new AttributeModifier("title", pageHelper
				.getStringLabel("baseGeralPage.link.home.title")));

		add(linkInicio);
	}

	private void addLinkFaleConosco() {

		final AjaxLink<Object> linkFale = new AjaxLink<Object>(
				"linkFaleConosco") {

			private static final long serialVersionUID = -8929835674488372843L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(FaleConoscoPage.class);
			}

		};
		linkFale.add(new Label("labelLinkfaleConosco", pageHelper
				.getStringLabel("faleConoscoPage.dominio")));

		linkFale.add(new AttributeModifier("title", pageHelper
				.getStringLabel("baseGeralPage.link.fale.title")));

		add(linkFale);
	}

	private void addLinkLogin() {

		final AjaxLink<Object> linkLogin = new AjaxLink<Object>("linkLogin") {

			private static final long serialVersionUID = -8653160823462970845L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(LoginPage.class);
			}

		};
		linkLogin.add(new Label("labellinkLogin", pageHelper
				.getStringLabel("loginPage.dominio")));

		linkLogin.add(new AttributeModifier("title", pageHelper
				.getStringLabel("baseGeralPage.link.login.title")));

		add(linkLogin);
	}

	private void addLinkQuemSomos() {

		final AjaxLink<Object> linkQuem = new AjaxLink<Object>("linkQuemSomos") {

			private static final long serialVersionUID = -8929835674488372843L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(QuemSomosPage.class);
			}

		};
		linkQuem.add(new Label("labelLinkquemSomos", pageHelper
				.getStringLabel("quemSomosPage.dominio")));

		linkQuem.add(new AttributeModifier("title", pageHelper
				.getStringLabel("baseGeralPage.link.quem.title")));

		add(linkQuem);
	}

	private void addLinkExternoWicket() {

		final StringBuffer label = new StringBuffer();

		label.append(pageHelper.getStringLabel("baseGeralPage.label.copyright"));

		label.append(DataUtil.recuperarAnoAtual());
		label.append(" ");

		label.append(pageHelper
				.getStringLabel("baseGeralPage.link.powered.label"));

		add(new Label("labelCopyright", label.toString()));

		final ExternalLink linkWicket = new ExternalLink("linkWicket",
				"https://wicket.apache.org/");
		//FIXME: Alterar para buscar link de uma constante

		linkWicket.add(new Label("labellinkWicket", pageHelper
				.getStringLabel("baseGeralPage.link.wicket.label")));

		add(linkWicket);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		renderizaCSS(response);
		renderizaJS(response);
	}

	private void renderizaCSS(IHeaderResponse response) {
		response.renderCSSReference("common/css/geral.css");
		response.renderCSSReference("common/css/page_templates.css");
		response.renderCSSReference("common/css/style.css");

		response.renderCSSReference("common/css/jquery.fancybox-1.3.4.css");
		response.renderCSSReference("common/css/shortcodes.css");
	}

	private void renderizaJS(IHeaderResponse response) {
		response.renderJavaScriptReference("common/js/custom.js");
		response.renderJavaScriptReference("common/js/DD_belatedPNG_0.0.8a-min.js");

		response.renderJavaScriptReference("common/js/jquery.cookie.js");
		response.renderJavaScriptReference("common/js/jquery.cycle.all.min.js");
		response.renderJavaScriptReference("common/js/jquery.easing.1.3.js");
		response.renderJavaScriptReference("common/js/jquery-1.7.1.min.js");
		response.renderJavaScriptReference("common/js/jquery.jscrollpane.min.js");

		response.renderJavaScriptReference("common/js/menu.js");
	}

	@Override
	protected void onBeforeRender() {

		super.onBeforeRender();

		addOrReplace(new Label("pageTitle", getPageTitle()));

		criarLinkImg();

		criarMenu();
	}

	private String getPageTitle() {

		final StringBuffer titulo = new StringBuffer();

		if (StringUtils.isNotBlank(pageTitle)) {

			titulo.append(pageTitle);
			titulo.append(ConstantesCore.SEPERADOR_TITULO);
			titulo.append(ParametrosCore.TITULO);

		} else {

			titulo.append(ParametrosCore.TITULO);
			titulo.append(ConstantesCore.SEPERADOR_TITULO);
			titulo.append(ParametrosCore.SUB_TITULO);
		}
		return titulo.toString();
	}

	private void criarLinkImg() {

		final Link<Object> link = new Link<Object>(ID_LINK_IMG) {

			private static final long serialVersionUID = -1553589591538220026L;

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		};
		
		//FIXME: Alterar para buscar path da imagem de uma constante

		final ContextImage imagem = new ContextImage(ID_IMG,
				"common/images/logo.png");

		imagem.add(new AttributeModifier("id", "logo"));
		imagem.add(new AttributeModifier("alt", "Webly Theme"));

		link.add(imagem);

		addOrReplace(link);
	}

	private void criarMenu() {

		final MenuUsuarioLogado menuUsuarioLogado = (MenuUsuarioLogado) Session
				.get().getAttribute(SessionKey.MENU_USUARIO_LOGADO.getKey());

		List<MenuItem> menus = null;

		if (menuUsuarioLogado != null && menuUsuarioLogado.getMenus() != null) {
			menus = menuUsuarioLogado.getMenus();

		} else {
			menus = new FabricaMenu().criarMenuPublico();

			Session.get().setAttribute(SessionKey.MENU_USUARIO_LOGADO.getKey(),
					new MenuUsuarioLogado(menus));
		}
		addOrReplace(new Menu(ID_MENU, menus));
	}

	public PageHelper getPageHelper() {
		return pageHelper;
	}

	private void inicializarTitulos(final String dominio) {

		pageHelper = new PageHelper(dominio);

		setPageTitle(pageHelper.getDominio());

		setDescricaoTituloPagina(pageHelper.getDescricaoTituloPagina());

		setSubDescricaoTituloPagina(pageHelper.getSubDescricaoTituloPagina());
	}

	protected void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public void setDescricaoTituloPagina(final String descricaoTituloPagina) {
		addOrReplace(new Label("pageDescriptionTitle", descricaoTituloPagina));
	}

	public void setSubDescricaoTituloPagina(
			final String subDescricaoTituloPagina) {
		addOrReplace(new Label("pageSubDescriptionTitle",
				subDescricaoTituloPagina));
	}

	protected String getPageStringLabel(final String recurso) {
		return pageHelper.getPageStringLabel(recurso);
	}
}