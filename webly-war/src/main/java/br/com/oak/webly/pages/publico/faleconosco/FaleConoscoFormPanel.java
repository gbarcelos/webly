package br.com.oak.webly.pages.publico.faleconosco;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import br.com.oak.webly.core.enums.CamposFaleConoscoEnum;
import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.pages.validator.NomePessoaValidator;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoTextArea;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.ContadorBehavior;
import br.com.oak.wicket.ui.behavior.LabelBehavior;
import br.com.oak.wicket.ui.captcha.CaptchaPanel;

public class FaleConoscoFormPanel extends Panel {

	private static final long serialVersionUID = 8002234529298288795L;

	private CampoTexto<String> campoNome;

	private String mensagem;

	private PageHelper pageHelper;

	public FaleConoscoFormPanel(final String idPanel,
			final PageHelper pageHelper) {

		super(idPanel);

		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {
		criarCampoNome();
		criarCampoEmail();
		criarCampoAssunto();
		criarCampoMensagem();
		criarCampoCaptcha();
	}

	private void criarCampoNome() {

		final String label = pageHelper
				.getStringLabel(CamposFaleConoscoEnum.NOME);

		campoNome = new CampoTexto<String>("nome", true);

		campoNome.setLabel(new Model<String>(label));

		campoNome.adicionaLimit(CamposFaleConoscoEnum.NOME.getTamanhoMaximo());

		campoNome.add(new LabelBehavior());

		adicionarValidadorCampoNome(label);

		addOrReplace(campoNome);
	}

	private void criarCampoEmail() {

		final CampoTexto<String> campoEmail = new CampoTexto<String>("email",
				true);

		campoEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposFaleConoscoEnum.EMAIL)));

		campoEmail
				.adicionaLimit(CamposFaleConoscoEnum.EMAIL.getTamanhoMaximo());

		campoEmail.add(EmailAddressValidator.getInstance());

		campoEmail.add(new LabelBehavior());

		addOrReplace(campoEmail);
	}

	private void criarCampoAssunto() {

		final CampoTexto<String> campoAssunto = new CampoTexto<String>(
				"assunto", true);

		campoAssunto.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposFaleConoscoEnum.ASSUNTO)));

		campoAssunto.adicionaLimit(CamposFaleConoscoEnum.ASSUNTO
				.getTamanhoMaximo());

		campoAssunto.add(StringValidator.lengthBetween(5,
				CamposFaleConoscoEnum.ASSUNTO.getTamanhoMaximo()));

		campoAssunto.add(new LabelBehavior());

		addOrReplace(campoAssunto);
	}

	private void criarCampoMensagem() {

		final CampoTextArea<String> campoMensagem = new CampoTextArea<String>(
				"mensagem", true);

		campoMensagem.setDefaultModel(new PropertyModel<String>(this,
				"mensagem"));

		campoMensagem.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposFaleConoscoEnum.MENSAGEM)));

		campoMensagem.adicionaLimit(CamposFaleConoscoEnum.MENSAGEM
				.getTamanhoMaximo());

		campoMensagem.add(new LabelBehavior());

		campoMensagem.add(new ContadorBehavior());

		addOrReplace(campoMensagem);
	}

	private void criarCampoCaptcha() {

		String mensagemErro = ResourceUtil
				.recuperaMensagemErro(MensagemErro.CAPTCHA_INCORRETO
						.getCodigo());

		String labelLink = pageHelper.getStringLabel("captchaPanel.link.label");

		String textoLink = pageHelper.getStringLabel("captchaPanel.link.texto");

		String tituloLink = pageHelper
				.getStringLabel("captchaPanel.link.title");

		String labelCampo = pageHelper
				.getStringLabel("captchaPanel.campo.label");

		addOrReplace(new CaptchaPanel("panelCaptcha", mensagemErro, labelLink,
				textoLink, tituloLink, labelCampo));
	}

	private void adicionarValidadorCampoNome(final String labelCampo) {

		String msgErro = ResourceUtil.recuperaMensagemErro(
				MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
				labelCampo, CamposPessoaEnum.NOME.getTamanhoMinimo(),
				CamposPessoaEnum.NOME.getTamanhoMaximo());

		campoNome.add(new NomePessoaValidator(msgErro));
	}

	public String getMensagem() {
		return mensagem;
	}

	public TextField<String> getCampoNome() {
		return campoNome;
	}
}