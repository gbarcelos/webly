package br.com.oak.webly.pages.publico.faleconosco;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.oak.webly.core.mensagem.MensagemSucesso;
import br.com.oak.webly.core.service.FaleConoscoService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.FaleConoscoVo;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.LoadFocus;

public class FaleConoscoForm extends Form<FaleConoscoVo> {

	private static final long serialVersionUID = 8605433397486592952L;

	@SpringBean
	private FaleConoscoService service;

	private FeedbackPanel feedBack;

	private FaleConoscoFormPanel panel;

	private PageHelper pageHelper;

	public FaleConoscoForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<FaleConoscoVo>(
				new FaleConoscoVo()));

		this.pageHelper = pageHelper;

		feedBack = new FeedbackPanel("feedbackFale");
		feedBack.setOutputMarkupId(true);
		add(feedBack);

		add(new Label("lblTextoFale", getTextoFaleConosco()));

		criarBtnEnviar();

		inicializar();
	}

	private void inicializar() {

		setModelObject(new FaleConoscoVo());

		panel = new FaleConoscoFormPanel("formPanelFale", pageHelper);
		addOrReplace(panel);
	}

	private void criarBtnEnviar() {

		addOrReplace(new AjaxButton("btnEnviarFale", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_ENVIAR))) {

			private static final long serialVersionUID = 8196945627582534943L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {

				enviarMensagem();

				inicializar();

				info(ResourceUtil
						.recuperaMensagemErro(MensagemSucesso.MENSAGEM_ENVIADA_COM_SUCESSO
								.getCodigo()));

				target.add(FaleConoscoForm.this);
			}

			@Override
			protected void onError(final AjaxRequestTarget target,
					final Form<?> form) {
				target.add(feedBack);
			}
		});
	}

	private void enviarMensagem() {
		getModelObject().setMensagem(panel.getMensagem());
		service.enviar(getModelObject());
	}

	private String getTextoFaleConosco() {

		final StringBuilder texto = new StringBuilder();
		texto.append(pageHelper.getPageStringLabel("texto1"));
		texto.append(pageHelper.getPageStringLabel("texto2"));

		return texto.toString();
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