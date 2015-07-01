package br.com.oak.webly.pages.publico.logout;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.request.handler.RenderPageRequestHandler.RedirectPolicy;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.SessionKey;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "logoutPage", parteUrl = "/logout", extensao = ConstantesWeb.EXTENSAO_URL)
public class LogoutPage extends PaginaExternaPage {

	private static final long serialVersionUID = 7892058585826937206L;

	public LogoutPage() {

		setDescricaoTituloPagina(StringUtils.EMPTY);
		setSubDescricaoTituloPagina(StringUtils.EMPTY);

		Session.get().setAttribute(SessionKey.MENU_USUARIO_LOGADO.getKey(),
				null);

		AuthenticatedWebSession.get().invalidate();

		throw new RestartResponseException(new PageProvider(getApplication()
				.getHomePage()), RedirectPolicy.AUTO_REDIRECT);
	}
}