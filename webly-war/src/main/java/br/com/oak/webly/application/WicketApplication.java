package br.com.oak.webly.application;

import java.util.Collection;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.MountedMapper;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.settings.IResourceSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.springreference.SpringReferenceSupporter;

import br.com.oak.webly.authentication.WeblyAuthenticatedWebSession;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.pages.home.HomePage;
import br.com.oak.webly.pages.publico.erro.ErroInesperadoPage;
import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.wicket.util.GeradorUrl;
import br.com.oak.wicket.util.application.JQContributionConfigCustom;
import br.com.oak.wicket.util.application.MapWebPage;

import com.google.code.jqwicket.JQComponentOnBeforeRenderListener;

public class WicketApplication extends AuthenticatedWebApplication {

	@Override
	public void init() {

		super.init();

		IResourceSettings resourceSettings = getResourceSettings();

		resourceSettings.addResourceFolder(ConstantesWeb.SOURCE_FOLDER);

		resourceSettings.getStringResourceLoaders().add(
				new PropertiesResourceLoader());

		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		SpringReferenceSupporter.register(this);

		JQContributionConfigCustom config = new JQContributionConfigCustom();
		getComponentPreOnBeforeRenderListeners().add(
				new JQComponentOnBeforeRenderListener(config));

		getApplicationSettings().setInternalErrorPage(ErroInesperadoPage.class);

		getExceptionSettings().setUnexpectedExceptionDisplay(
				IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);

		registraAllWebPages();
	}

	private void registraAllWebPages() {

		final Collection<Class<? extends WebPage>> values = MapWebPage.get(
				ConstantesCore.PACOTE_RAIZ, ConstantesCore.PACOTE_PAGINAS)
				.values();

		for (final Class<? extends WebPage> classe : values) {
			root(new GeradorUrl(classe, ConstantesWeb.EXTENSAO_URL).gerar(),
					classe);
		}
	}

	private void root(final String urlBase,
			final Class<? extends WebPage> classe) {
		getRootRequestMapperAsCompound()
				.add(new MountedMapper(urlBase, classe));
		mountPage(urlBase, classe);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return WeblyAuthenticatedWebSession.class;
	}
}