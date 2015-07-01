package br.com.oak.webly.pages.publico.usuario;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.pages.validator.CodigoVerificacaoValidator;
import br.com.oak.webly.pages.validator.NomeUsuarioEmailValidator;
import br.com.oak.webly.pages.validator.PasswordValidator;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoPassword;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

public class ConfirmaUsuarioFormPanel extends Panel {

	private static final long serialVersionUID = -1253087596858705760L;

	private CampoTexto<String> campoNomeUsuarioOuEmail;

	public ConfirmaUsuarioFormPanel(final String idPanel,
			final PageHelper pageHelper) {

		super(idPanel);

		inicializar(pageHelper);
	}

	private void inicializar(final PageHelper pageHelper) {
		criarCampoNome(pageHelper);
		criarCampoCodigoVerificacao(pageHelper);
		criarCampoSenha(pageHelper);
	}

	private void criarCampoNome(final PageHelper pageHelper) {

		campoNomeUsuarioOuEmail = new CampoTexto<String>("nomeUsuarioOuEmail",
				true);

		campoNomeUsuarioOuEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL)));

		adicionarValidadorCampoNomeUsuarioOuEmail(pageHelper);

		campoNomeUsuarioOuEmail
				.adicionaLimit(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL
						.getTamanhoMaximo());

		campoNomeUsuarioOuEmail.add(new LabelBehavior());

		addOrReplace(campoNomeUsuarioOuEmail);
	}

	private void criarCampoCodigoVerificacao(final PageHelper pageHelper) {

		final CampoTexto<String> campoVerificacao = new CampoTexto<String>(
				"codigoVerificacao", true);

		campoVerificacao.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.CODIGO_VERIFICACAO)));

		adicionarValidadorCodigoVerificacao(campoVerificacao, pageHelper);

		campoVerificacao.adicionaLimit(CamposUsuarioEnum.CODIGO_VERIFICACAO
				.getTamanhoMaximo());

		campoVerificacao.add(new LabelBehavior());

		addOrReplace(campoVerificacao);
	}

	private void criarCampoSenha(final PageHelper pageHelper) {

		final CampoPassword campoSenha = new CampoPassword("password", true);

		campoSenha.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.SENHA)));

		adicionarValidadorCampoSenha(campoSenha, pageHelper);

		campoSenha.adicionaLimit(CamposUsuarioEnum.SENHA.getTamanhoMaximo());

		campoSenha.add(new LabelBehavior());

		add(campoSenha);
	}

	private void adicionarValidadorCampoNomeUsuarioOuEmail(
			final PageHelper pageHelper) {

		final String msgErro = ResourceUtil
				.recuperaMensagemErro(
						MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO.getCodigo(),
						pageHelper
								.getStringLabel(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL),
						CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL
								.getTamanhoMaximo());

		campoNomeUsuarioOuEmail.add(new NomeUsuarioEmailValidator(msgErro));
	}

	private void adicionarValidadorCodigoVerificacao(
			final CampoTexto<String> campo, final PageHelper pageHelper) {

		final String msgErro = ResourceUtil
				.recuperaMensagemErro(MensagemErro.TAMANHO_CAMPO_INVALIDO
						.getCodigo(), pageHelper
						.getStringLabel(CamposUsuarioEnum.CODIGO_VERIFICACAO),
						CamposUsuarioEnum.CODIGO_VERIFICACAO.getTamanhoMaximo());
		campo.add(new CodigoVerificacaoValidator(msgErro));
	}

	private void adicionarValidadorCampoSenha(final CampoPassword campoSenha,
			final PageHelper pageHelper) {

		final String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				pageHelper.getStringLabel(CamposUsuarioEnum.SENHA),
				CamposUsuarioEnum.SENHA.getTamanhoMinimo(),
				CamposUsuarioEnum.SENHA.getTamanhoMaximo());
		campoSenha.add(new PasswordValidator(msgErro));
	}

	public TextField<String> getCampoNome() {
		return campoNomeUsuarioOuEmail;
	}
}