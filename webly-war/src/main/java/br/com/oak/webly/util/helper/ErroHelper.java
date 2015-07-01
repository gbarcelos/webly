package br.com.oak.webly.util.helper;

import org.apache.wicket.model.ResourceModel;

import br.com.oak.core.exception.Erro;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;

/**
 * Responsável por tratar o objeto Erro, que originou-se na camada de negócio
 * 
 *
 */
public class ErroHelper {

	public String getMensagemErro(final Erro erro) {
		return ResourceUtil
				.recuperaMensagemErro(erro.getCodigo(), traduzirParametros(erro));
	}

	public String getMensagemErroInesperado() {
		return ResourceUtil.recuperaMensagemErro(MensagemErro.ERRO_INESPERADO.getCodigo());
	}

	private Object[] traduzirParametros(final Erro erro) {

		final Object[] parametros = erro.getParametros();

		if (erro.getCampo() != null) {
			parametros[0] = new ResourceModel(erro.getCampo().getDescricao())
					.getObject();
		}
		return parametros;
	}
}