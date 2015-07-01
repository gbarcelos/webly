package br.com.oak.webly.pages.publico.usuario;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.pages.validator.EmailValidator;
import br.com.oak.webly.pages.validator.NomePessoaValidator;
import br.com.oak.webly.pages.validator.PasswordValidator;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoPassword;
import br.com.oak.wicket.ui.basico.CampoTexto;

public class NovoUsuarioFormPanel extends Panel {

	private static final long serialVersionUID = -6172160141004126117L;

	private CampoTexto<String> campoNome;

	public NovoUsuarioFormPanel(final String idPanel,
			final PageHelper pageHelper) {
		super(idPanel);
		inicializar(pageHelper);
	}

	private void inicializar(final PageHelper pageHelper) {
		criarCampoNome(pageHelper);
		criarCampoEmail(pageHelper);
		criarCampoSenha(pageHelper);
	}

	private void criarCampoNome(final PageHelper pageHelper) {

		final String label = pageHelper.getStringLabel(CamposPessoaEnum.NOME);

		campoNome = new CampoTexto<String>("nome", true);

		campoNome.setLabel(new Model<String>(label));

		campoNome.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, label));

		adicionarValidadorCampoNome(label);

		campoNome.adicionaLimit(CamposPessoaEnum.NOME.getTamanhoMaximo());

		add(campoNome);
	}

	private void criarCampoEmail(final PageHelper pageHelper) {

		final String label = pageHelper.getStringLabel(CamposPessoaEnum.EMAIL);

		final CampoTexto<String> campoEmail = new CampoTexto<String>("email",
				true);

		campoEmail.setLabel(new Model<String>(label));

		campoEmail.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, label));

		adicionarValidadorCampoEmail(campoEmail, pageHelper);

		campoEmail.adicionaLimit(CamposPessoaEnum.EMAIL.getTamanhoMaximo());

		add(campoEmail);
	}

	private void criarCampoSenha(final PageHelper pageHelper) {

		final String label = pageHelper.getStringLabel(CamposUsuarioEnum.SENHA);

		final CampoPassword campoSenha = new CampoPassword("password", true);

		campoSenha.setLabel(new Model<String>(label));
		campoSenha.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, label));

		adicionarValidadorCampoSenha(campoSenha, pageHelper);
		campoSenha.adicionaLimit(CamposUsuarioEnum.SENHA.getTamanhoMaximo());

		add(campoSenha);
	}

	private void adicionarValidadorCampoNome(final String labelCampo) {

		String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				labelCampo, CamposPessoaEnum.NOME.getTamanhoMinimo(),
				CamposPessoaEnum.NOME.getTamanhoMaximo());

		campoNome.add(new NomePessoaValidator(msgErro));
	}

	private void adicionarValidadorCampoEmail(
			final CampoTexto<String> campoEmail, final PageHelper pageHelper) {

		final String msgErroTamanho = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO.getCodigo(),
				pageHelper.getStringLabel(CamposUsuarioEnum.EMAIL),
				CamposPessoaEnum.EMAIL.getTamanhoMaximo());

		campoEmail.add(new EmailValidator(msgErroTamanho));
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
		return campoNome;
	}
}