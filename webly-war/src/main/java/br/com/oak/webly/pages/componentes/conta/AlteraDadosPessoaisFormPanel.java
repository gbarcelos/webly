package br.com.oak.webly.pages.componentes.conta;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

public class AlteraDadosPessoaisFormPanel extends Panel {

	private static final long serialVersionUID = 5607533828572882318L;

	private CampoTexto<String> campoNome;

	private PageHelper pageHelper;

	public AlteraDadosPessoaisFormPanel(final String idPanel,
			final PageHelper pageHelper) {

		super(idPanel);

		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {
		criarCampoNome();
		criarCampoEmail();
	}

	private void criarCampoNome() {

		campoNome = new CampoTexto<String>("nome", true);

		campoNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPessoaEnum.NOME)));

		campoNome.adicionaLimit(CamposPessoaEnum.NOME.getTamanhoMaximo());
		
		campoNome.add(new LabelBehavior());

		addOrReplace(campoNome);
	}

	private void criarCampoEmail() {

		final CampoTexto<String> campoEmail = new CampoTexto<String>("email",
				true);

		campoEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.EMAIL)));

		campoEmail.adicionaLimit(CamposUsuarioEnum.EMAIL.getTamanhoMaximo());

		campoEmail.add(new LabelBehavior());

		addOrReplace(campoEmail);
	}

	public Component getCampoFoco() {
		return campoNome;
	}
}
