package br.com.oak.webly.pages.componentes.conta;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.pages.validator.PasswordValidator;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoPassword;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

public class AlteraUsuarioFormPanel extends Panel {

	private static final long serialVersionUID = 5166017316485908549L;

	private CampoTexto<String> campoNome;

	private CampoPassword campoNovaSenha;

	private CampoPassword campoConfirmaNovaSenha;

	private PageHelper pageHelper;

	public AlteraUsuarioFormPanel(final String idPanel,
			final PageHelper pageHelper) {

		super(idPanel);

		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {
		criarCampoNome();
		criarCampoNomeExibicao();
		criarCampoNovaSenha();
		criarCampoConfirmacaoNovaSenha();
	}

	private void criarCampoNome() {

		campoNome = new CampoTexto<String>("nome", true);

		campoNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.NOME_USUARIO)));

		campoNome.adicionaLimit(CamposUsuarioEnum.NOME_USUARIO
				.getTamanhoMaximo());

		campoNome.add(new LabelBehavior());

		addOrReplace(campoNome);
	}

	private void criarCampoNomeExibicao() {

		final CampoTexto<String> campoNomePublico = new CampoTexto<String>(
				"nomePublico", true);

		campoNomePublico.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.NOME_PUBLICO_USUARIO)));

		campoNomePublico.add(new LabelBehavior());

		addOrReplace(campoNomePublico);
	}

	private void criarCampoNovaSenha() {

		campoNovaSenha = new CampoPassword("novaSenha");

		campoNovaSenha.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.NOVA_SENHA)));

		campoNovaSenha.adicionaLimit(CamposUsuarioEnum.NOVA_SENHA
				.getTamanhoMaximo());

		campoNovaSenha.add(new LabelBehavior());

		final String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				pageHelper.getStringLabel(CamposUsuarioEnum.NOVA_SENHA),
				CamposUsuarioEnum.NOVA_SENHA.getTamanhoMinimo(),
				CamposUsuarioEnum.NOVA_SENHA.getTamanhoMaximo());

		campoNovaSenha.add(new PasswordValidator(msgErro));

		add(campoNovaSenha);
	}

	private void criarCampoConfirmacaoNovaSenha() {

		campoConfirmaNovaSenha = new CampoPassword("confirmaNovaSenha");

		campoConfirmaNovaSenha.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.CONFIRMA_NOVA_SENHA)));

		campoConfirmaNovaSenha
				.adicionaLimit(CamposUsuarioEnum.CONFIRMA_NOVA_SENHA
						.getTamanhoMaximo());

		campoConfirmaNovaSenha.add(new LabelBehavior());

		final String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				pageHelper
						.getStringLabel(CamposUsuarioEnum.CONFIRMA_NOVA_SENHA),
				CamposUsuarioEnum.CONFIRMA_NOVA_SENHA.getTamanhoMinimo(),
				CamposUsuarioEnum.CONFIRMA_NOVA_SENHA.getTamanhoMaximo());

		campoConfirmaNovaSenha.add(new PasswordValidator(msgErro));

		add(campoConfirmaNovaSenha);
	}

	public PasswordTextField getCampoNovaSenha() {
		return campoNovaSenha;
	}

	public PasswordTextField getCampoConfirmaNovaSenha() {
		return campoConfirmaNovaSenha;
	}

	public Component getCampoFoco() {
		return campoNome;
	}
}