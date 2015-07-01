package br.com.oak.webly.pages.publico.post;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.AjaxLazyLoadPanel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.string.UrlUtils;

public class ComentariosLazyLoadPanel extends AjaxLazyLoadPanel {

	private static final long serialVersionUID = 8028400532268890368L;

	public ComentariosLazyLoadPanel(final String idPanel) {
		super(idPanel);
	}

	@Override
	public Component getLazyLoadComponent(final String markupId) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return new ComentariosPanel(markupId);
	}

	@Override
	public Component getLoadingComponent(final String markupId) {
		
		//FIXME: Alterar para path vir de uma constante

		final String pathGif = UrlUtils.rewriteToContextRelative(
				"common/images/ajax-loader.gif", RequestCycle.get());

		return new Label(markupId, "<div style=\"text-align:center;\"><img alt=\"Carregando...\" src=\""
				+ pathGif + "\"/></div>").setEscapeModelStrings(false);
	}
}