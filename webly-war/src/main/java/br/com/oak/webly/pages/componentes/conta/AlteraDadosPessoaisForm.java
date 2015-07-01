package br.com.oak.webly.pages.componentes.conta;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.CollectionUtils;

import br.com.oak.core.exception.Erro;
import br.com.oak.core.exception.OakException;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.model.dbwebly.Pessoa;
import br.com.oak.webly.core.service.PessoaService;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.ErroHelper;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.LoadFocus;

public abstract class AlteraDadosPessoaisForm extends Form<Pessoa> {

	private static final long serialVersionUID = -2397506478519416206L;

	@SpringBean
	private PessoaService service;

	private FeedbackPanel feedBack;

	private AlteraDadosPessoaisFormPanel panel;

	private PageHelper pageHelper;

	public AlteraDadosPessoaisForm(final String idForm, final Long codigo,
			final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<Pessoa>(new Pessoa()));
		setOutputMarkupId(true);

		this.pageHelper = pageHelper;

		setModelObject(service.obter(codigo));

		inicializar();
	}

	private void inicializar() {

		feedBack = new FeedbackPanel("feedbackADP");
		feedBack.setOutputMarkupId(true);
		add(feedBack);

		panel = new AlteraDadosPessoaisFormPanel("formPanelADP", pageHelper);
		add(panel);

		criarBtnSalvar();
		criarBtnVoltar();
	}

	private void criarBtnSalvar() {

		addOrReplace(new AjaxButton("btnSalvarADP", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_SALVAR))) {

			private static final long serialVersionUID = -2522557906547027264L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				try {
					salvar();

					//FIXME: msg sucesso

					voltar();

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

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedBack);
			}
		});
	}

	private void criarBtnVoltar() {

		final Button btnVoltar = new Button("btnVoltarADP", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_VOLTAR))) {

			private static final long serialVersionUID = 1292209494796386265L;

			@Override
			public void onSubmit() {
				voltar();
			}
		};
		btnVoltar.setDefaultFormProcessing(false);
		addOrReplace(btnVoltar);
	}

	private void salvar() {
		service.alterar(getModelObject());
	}

	protected abstract void voltar();

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnLoadJavaScript(LoadFocus.getFocusScript(panel
				.getCampoFoco()));
	}
}