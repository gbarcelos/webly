package br.com.oak.webly.pages.componentes.conta;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

public abstract class ConsultaContaUsuarioFormPanel extends Panel {

	private static final long serialVersionUID = -5353195028962471461L;

	private FeedbackPanel feedback;

	private PageHelper pageHelper;

	public ConsultaContaUsuarioFormPanel(final String idPanel,
			final FeedbackPanel feedBack, final PageHelper pageHelper) {
		super(idPanel);
		setOutputMarkupId(true);

		this.feedback = feedBack;
		this.pageHelper = pageHelper;

		inicializar();
	}

	private void inicializar() {
		criarTitulosSessoes();
		criarCampoNomePessoa();
		criarCampoNome();
		criarCampoEmail();
		criarCampoTipoUsuario();
		criarCampoMembroDesde();
		criarBtnAlterarDadosPessoais();
		criarBtnAlterarDadosUsuario();
	}

	private void criarBtnAlterarDadosUsuario() {

		final AjaxButton btn = new AjaxButton("btnAlterarDadosUsuario",
				new Model<String>(pageHelper
						.getStringLabel(ConstantesWeb.LABEL_BTN_ALTERAR))) {

			private static final long serialVersionUID = -8312076247125068786L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				alterarDadosUsuario();
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		btn.add(new AttributeModifier(ConstantesWeb.ATTRIBUTE_TITLE, "Alterar "
				+ pageHelper.getPageStringLabel("titulo.sessaoUsuario")));

		addOrReplace(btn);
	}

	private void criarBtnAlterarDadosPessoais() {

		final AjaxButton btn = new AjaxButton("btnAlterarDadosPessoais",
				new Model<String>(pageHelper
						.getStringLabel(ConstantesWeb.LABEL_BTN_ALTERAR))) {

			private static final long serialVersionUID = -8312076247125068786L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				alterarDadosPessoais();
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		};
		btn.add(new AttributeModifier(ConstantesWeb.ATTRIBUTE_TITLE, "Alterar "
				+ pageHelper.getPageStringLabel("titulo.sessaoPessoa")));

		addOrReplace(btn);
	}

	private void criarTitulosSessoes() {

		addOrReplace(new Label("tituloSessaoPessoa",
				pageHelper.getPageStringLabel("titulo.sessaoPessoa")));

		addOrReplace(new Label("tituloSessaoUsuario",
				pageHelper.getPageStringLabel("titulo.sessaoUsuario")));
	}

	private void criarCampoNomePessoa() {

		final TextField<String> campoNome = new TextField<String>("nomePessoa");

		campoNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPessoaEnum.NOME)));

		campoNome.setEnabled(false);

		campoNome.add(new LabelBehavior());

		addOrReplace(campoNome);
	}

	private void criarCampoNome() {

		final TextField<String> campoNome = new TextField<String>("nome");

		campoNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.NOME_USUARIO)));

		campoNome.setEnabled(false);

		campoNome.add(new LabelBehavior());

		addOrReplace(campoNome);
	}

	private void criarCampoEmail() {

		final TextField<String> campoEmail = new TextField<String>("email");

		campoEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.EMAIL)));

		campoEmail.setEnabled(false);

		campoEmail.add(new LabelBehavior());

		addOrReplace(campoEmail);
	}

	private void criarCampoTipoUsuario() {

		final TextField<String> campoTipoUsuario = new TextField<String>(
				"tipoUsuario.descricao");

		campoTipoUsuario.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.TIPO_USUARIO)));

		campoTipoUsuario.setEnabled(false);

		campoTipoUsuario.add(new LabelBehavior());

		addOrReplace(campoTipoUsuario);
	}

	private void criarCampoMembroDesde() {

		final TextField<String> campoMembroDesde = new TextField<String>(
				"dataInclusaoExtenso");

		campoMembroDesde.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.MEMBRO_DESDE)));

		campoMembroDesde.setEnabled(false);

		campoMembroDesde.add(new LabelBehavior());

		addOrReplace(campoMembroDesde);
	}

	protected abstract void alterarDadosUsuario();

	protected abstract void alterarDadosPessoais();
}
