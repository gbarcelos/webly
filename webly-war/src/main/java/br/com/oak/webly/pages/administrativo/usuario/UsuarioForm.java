package br.com.oak.webly.pages.administrativo.usuario;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
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
import br.com.oak.webly.core.mensagem.MensagemSucesso;
import br.com.oak.webly.core.model.dbwebly.Usuario;
import br.com.oak.webly.core.service.AuxiliarService;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.ErroHelper;
import br.com.oak.webly.util.helper.PageHelper;

public class UsuarioForm extends Form<Usuario> {

	private static final long serialVersionUID = -4599302945738402558L;

	@SpringBean
	private UsuarioService service;

	@SpringBean
	private AuxiliarService auxiliarService;

	private FeedbackPanel feedBack;

	private UsuarioFormPanel panel;

	private PageHelper pageHelper;

	public UsuarioForm(final String idForm, final Long codigo,
			final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<Usuario>(new Usuario()));
		setOutputMarkupId(true);

		this.pageHelper = pageHelper;

		setModelObject(service.obter(codigo));

		inicializar();
	}

	public void inicializar() {

		feedBack = new FeedbackPanel("feedbackCAUSR");
		feedBack.setOutputMarkupId(true);
		add(feedBack);

		criarPanelUsuario();
		criarBtnSalvar();
		criarBtnVoltar();
	}

	private void criarPanelUsuario() {
		panel = new UsuarioFormPanel("formPanelCAUSR", auxiliarService,
				pageHelper);
		add(panel);
	}

	private void criarBtnSalvar() {

		addOrReplace(new AjaxButton("btnSalvarCAUSR", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_SALVAR))) {

			private static final long serialVersionUID = 8758759584169705542L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				try {
					salvar();

					info(ResourceUtil
							.recuperaMensagemErro(MensagemSucesso.OPERACAO_COM_SUCESSO
									.getCodigo()));
					setResponsePage(new ConsultaUsuarioPage());

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
				target.add(feedBack);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedBack);
			}
		});
	}

	private void criarBtnVoltar() {

		final Button btnVoltar = new Button("btnVoltarCAUSR",
				new Model<String>(pageHelper
						.getStringLabel(ConstantesWeb.LABEL_BTN_VOLTAR))) {

			private static final long serialVersionUID = 3079304654236392183L;

			@Override
			public void onSubmit() {
				setResponsePage(ConsultaUsuarioPage.class);
			}
		};
		btnVoltar.setDefaultFormProcessing(false);
		addOrReplace(btnVoltar);
	}

	private void salvar() {
		service.alterar(getModelObject());
	}
}