package br.com.oak.webly.pages.publico.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.pages.validator.NomeUsuarioEmailValidator;
import br.com.oak.webly.pages.validator.PasswordValidator;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoPassword;
import br.com.oak.wicket.ui.basico.CampoTexto;

public class LoginFormPanel extends Panel {

	private static final long serialVersionUID = 8025370683614272733L;

	private CampoTexto<String> campoNomeUsuarioOuEmail;

	private PageHelper pageHelper;

	public LoginFormPanel(final String idPanel, final PageHelper pageHelper) {

		super(idPanel);
		setOutputMarkupId(true);

		this.pageHelper = pageHelper;
		inicializar();
	}

	private void inicializar() {
		montarCampoNomeUsuarioOuEmail();
		montarCampoSenha();
	}

	private void montarCampoNomeUsuarioOuEmail() {

		final String label = pageHelper
				.getStringLabel(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL);

		campoNomeUsuarioOuEmail = new CampoTexto<String>("nomeUsuarioOuEmail",
				true);
		campoNomeUsuarioOuEmail.setLabel(new Model<String>(label));
		campoNomeUsuarioOuEmail.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, label));
		campoNomeUsuarioOuEmail
				.adicionaLimit(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL
						.getTamanhoMaximo());

		adicionarValidadorCampoNomeUsuarioOuEmail();

		add(campoNomeUsuarioOuEmail);
	}

	private void montarCampoSenha() {

		final String label = pageHelper.getStringLabel(CamposUsuarioEnum.SENHA);

		final CampoPassword campoSenha = new CampoPassword("password",
				true);

		campoSenha.setLabel(new Model<String>(label));
		campoSenha.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, label));

		adicionarValidadorCampoSenha(campoSenha);

		campoSenha.adicionaLimit(CamposUsuarioEnum.SENHA.getTamanhoMaximo());
		add(campoSenha);
	}

	private void adicionarValidadorCampoNomeUsuarioOuEmail() {

		final String msgErro = ResourceUtil
				.recuperaMensagemErro(
						MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO.getCodigo(),
						pageHelper
								.getStringLabel(CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL),
						CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL
								.getTamanhoMaximo());

		campoNomeUsuarioOuEmail.add(new NomeUsuarioEmailValidator(msgErro));
	}

	private void adicionarValidadorCampoSenha(final CampoPassword campoSenha) {

		final String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				pageHelper.getStringLabel(CamposUsuarioEnum.SENHA),
				CamposUsuarioEnum.SENHA.getTamanhoMinimo(),
				CamposUsuarioEnum.SENHA.getTamanhoMaximo());

		campoSenha.add(new PasswordValidator(msgErro));
	}

	public TextField<String> getCampoNomeUsuarioOuEmail() {
		return campoNomeUsuarioOuEmail;
	}
}