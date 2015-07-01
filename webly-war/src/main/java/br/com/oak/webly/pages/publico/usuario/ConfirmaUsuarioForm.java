package br.com.oak.webly.pages.publico.usuario;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.springframework.util.CollectionUtils;

import br.com.oak.core.exception.Erro;
import br.com.oak.core.exception.OakException;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.vo.ConfirmaUsuarioVo;
import br.com.oak.webly.pages.publico.login.LoginPage;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.ErroHelper;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.LoadFocus;

public class ConfirmaUsuarioForm extends Form<ConfirmaUsuarioVo> {

	private static final long serialVersionUID = -2210979096835904623L;

	@SpringBean
	private UsuarioService service;

	private FeedbackPanel feedback;

	private ConfirmaUsuarioFormPanel panel;

	private PageHelper pageHelper;

	public ConfirmaUsuarioForm(final String idForm,
			final StringValue nomeUsuario, final StringValue codigoVerificacao,
			final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<ConfirmaUsuarioVo>(
				new ConfirmaUsuarioVo(nomeUsuario.toString(),
						codigoVerificacao.toString())));

		this.pageHelper = pageHelper;

		feedback = new FeedbackPanel("feedbackConf");
		feedback.setOutputMarkupId(true);
		add(feedback);

		panel = new ConfirmaUsuarioFormPanel("formPanelConf", pageHelper);
		addOrReplace(panel);

		criarBtnOk();
	}

	private void criarBtnOk() {

		addOrReplace(new AjaxButton("btnOk", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_SALVAR))) {

			private static final long serialVersionUID = 8196945627582534943L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {

				confirmarUsuario();
			}

			@Override
			protected void onError(final AjaxRequestTarget target,
					final Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private void confirmarUsuario() {

		try {

			service.confirmarUsuario(getModelObject());

			setResponsePage(new LoginPage());

		} catch (WeblyNegocioException e) {

			if (!CollectionUtils.isEmpty(e.getErros())) {

				for (final Erro erro : e.getErros()) {
					error(new ErroHelper().getMensagemErro(erro));
				}

			} else {
				error(e.getMessage());
			}

		} catch (OakException e) {
			error(new ErroHelper().getMensagemErroInesperado());

		} catch (Exception e) {
			error(new ErroHelper().getMensagemErroInesperado());

		}
	}

	/**
	 * Direciona o foco para o primeiro campo do formulário
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnLoadJavaScript(LoadFocus.getFocusScript(panel
				.getCampoNome()));
	}
}