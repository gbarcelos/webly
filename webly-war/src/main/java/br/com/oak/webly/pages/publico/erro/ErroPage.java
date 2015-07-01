package br.com.oak.webly.pages.publico.erro;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "erroPage", parteUrl = "/publico/erro", extensao = ConstantesWeb.EXTENSAO_URL)
public class ErroPage extends PaginaExternaPage {

	private static final long serialVersionUID = 1541083362464359296L;

	public ErroPage(final PageParameters parameters) {

		super("erro");

		tratarErro(parameters.get(ConstantesWeb.PARAMETRO_GET_ERRO).toString());
	}

	private void tratarErro(final String codigoErro) {

		if (StringUtils.isNotBlank(codigoErro)) {

			setDescricaoTituloPagina(getPageStringLabel(codigoErro + ".texto1"));

			setSubDescricaoTituloPagina(StringUtils.EMPTY);

			add(new Label("codigoErro", "Erro " + codigoErro));

			add(new Label("descricaoErro", getPageStringLabel(codigoErro
					+ ".texto2")));
		}
	}
}