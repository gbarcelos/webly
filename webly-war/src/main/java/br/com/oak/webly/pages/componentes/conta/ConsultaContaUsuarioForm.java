package br.com.oak.webly.pages.componentes.conta;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.oak.webly.core.vo.UsuarioLogado;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.webly.util.helper.PageHelper;

public abstract class ConsultaContaUsuarioForm extends Form<UsuarioLogado> {

	private static final long serialVersionUID = -2907110769398686829L;

	public ConsultaContaUsuarioForm(final String idForm,
			final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<UsuarioLogado>(
				AuthenticationUtils.getUsuarioLogado()));

		final FeedbackPanel feedBack = new FeedbackPanel("feedbackCCU");
		feedBack.setOutputMarkupId(true);
		add(feedBack);

		add(new ConsultaContaUsuarioFormPanel("formPanelCCU", feedBack,
				pageHelper) {

			private static final long serialVersionUID = 2234874125624738884L;

			@Override
			protected void alterarDadosUsuario() {
				alterarUsuario();
			}

			@Override
			protected void alterarDadosPessoais() {
				alterarPessoa();
			}
		});
	}

	private void alterarUsuario() {
		alterarDadosUsuario(getModelObject().getCodigo());
	}

	private void alterarPessoa() {
		alterarDadosPessoais(getModelObject().getCodigoPessoa());
	}

	protected abstract void alterarDadosUsuario(Long codigo);

	protected abstract void alterarDadosPessoais(Long codigo);
}