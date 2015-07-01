package br.com.oak.webly.pages.administrativo.usuario;

import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.enums.SimNaoEnum;
import br.com.oak.webly.core.model.dbwebly.TipoUsuario;
import br.com.oak.webly.core.service.AuxiliarService;
import br.com.oak.webly.pages.componentes.SimNaoRenderer;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.ComboBox;
import br.com.oak.wicket.ui.basico.Radio;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

public class UsuarioFormPanel extends Panel {

	private static final long serialVersionUID = -3821387127928652050L;

	private TextField<String> campoNome;

	private AuxiliarService auxiliarService;

	private PageHelper pageHelper;

	public UsuarioFormPanel(final String idPanel,
			final AuxiliarService auxiliarService, final PageHelper pageHelper) {

		super(idPanel);

		this.auxiliarService = auxiliarService;
		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {
		criarCampoNome();
		criarCampoEmail();
		criarCampoTipoUsuario();
		criarCampoAtivoInativo();
	}

	private void criarCampoNome() {

		campoNome = new TextField<String>("pessoa.nome");

		campoNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPessoaEnum.NOME)));

		campoNome.setEnabled(false);

		campoNome.add(new LabelBehavior());

		addOrReplace(campoNome);
	}

	private void criarCampoEmail() {

		final TextField<String> campoEmail = new TextField<String>(
				"pessoa.email");

		campoEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.EMAIL)));

		campoEmail.setEnabled(false);

		campoEmail.add(new LabelBehavior());

		addOrReplace(campoEmail);
	}

	private void criarCampoTipoUsuario() {

		final ComboBox<TipoUsuario> comboTipoUsuario = new ComboBox<TipoUsuario>(
				"tipoUsuario", auxiliarService.listarTiposDeUsuarioAtivos(),
				new ChoiceRenderer<TipoUsuario>("descricao", "codigo"));

		comboTipoUsuario.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.TIPO_USUARIO)));

		comboTipoUsuario.add(new LabelBehavior());

		add(comboTipoUsuario);
	}

	private void criarCampoAtivoInativo() {

		final Radio<SimNaoEnum> radio = new Radio<SimNaoEnum>(
				"statusRegistroAtivo", Arrays.asList(SimNaoEnum.values()),
				new SimNaoRenderer(), true);

		radio.setOutputMarkupId(true);

		radio.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.USUARIO_ATIVO_INATIVO)));

		radio.add(new LabelBehavior());

		add(radio);
	}

	public Component getCampoFoco() {
		return campoNome;
	}
}
