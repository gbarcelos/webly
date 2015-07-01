package br.com.oak.webly.util.helper;

import java.io.Serializable;

import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.Request;

import br.com.oak.core.enums.CamposEnum;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.util.ConstantesWeb;

/**
 * Responsável por recuperar textos necessários em cada página.
 * 
 *
 */
public class PageHelper implements Serializable {

	private static final long serialVersionUID = 5958393947019671083L;

	private static final int QTD_PARAM = 2;

	private static final int IX_MODULO_CONF = 1;

	private static final int IX_RAIZ = 0;

	private String chave;

	public PageHelper() {
	}

	public PageHelper(final String chave) {
		this.chave = chave;
	}

	public String getStringLabel(final String recurso) {
		return new ResourceModel(recurso).getObject();
	}

	public String getStringLabel(final CamposEnum campoEnum) {
		return getStringLabel(campoEnum.getDescricao());
	}

	public String getPageStringLabel(final String recurso) {
		return new ResourceModel(chave + "Page." + recurso).getObject();
	}	

	public String getUrl(final String urlParcial, final Request request) {

		final StringBuilder url = new StringBuilder();

		url.append(getHost(request));
		url.append(request.getContextPath());
		url.append(getPath(request));
		url.append(urlParcial);

		return url.toString();
	}

	private Object getHost(final Request request) {

		final StringBuilder host = new StringBuilder();

		host.append(request.getClientUrl().getProtocol());

		host.append(ConstantesCore.SEPARADOR_PROTOCOLO
				+ ConstantesCore.SEPARADOR_BARRA_BARRA);

		host.append(request.getClientUrl().getHost());

		if (request.getClientUrl().getPort() != ConstantesWeb.PORTA_PADRAO) {
			host.append(ConstantesCore.SEPARADOR_PROTOCOLO);
			host.append(request.getClientUrl().getPort());
		}
		return host.toString();
	}

	private String getPath(final Request request) {

		final StringBuilder path = new StringBuilder();

		final String[] partesUrl = request.getClientUrl().getPath()
				.split(ConstantesCore.SEPARADOR_URL);

		if ((partesUrl != null) && (partesUrl.length >= QTD_PARAM)) {

			path.append(ConstantesCore.SEPARADOR_URL);
			path.append(partesUrl[IX_RAIZ]);

			path.append(ConstantesCore.SEPARADOR_URL);
			path.append(partesUrl[IX_MODULO_CONF]);

			path.append(ConstantesCore.SEPARADOR_URL);
		}
		return path.toString();
	}

	public String getDominio() {
		return getPageStringLabel("dominio");
	}

	public String getDescricaoTituloPagina() {
		return getPageStringLabel("descricaoTituloPagina");
	}

	public String getSubDescricaoTituloPagina() {
		return getPageStringLabel("subDescricaoTituloPagina");
	}
}