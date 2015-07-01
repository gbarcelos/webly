package br.com.oak.webly.pages.publico.usuario;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.util.CollectionUtils;

import br.com.oak.core.exception.Erro;
import br.com.oak.core.exception.OakException;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.mensagem.MensagemSucesso;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.NovoUsuarioVo;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.ErroHelper;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.LoadFocus;

public class NovoUsuarioForm extends Form<NovoUsuarioVo> {

	private static final long serialVersionUID = -1845116409083107203L;

	@SpringBean
	private UsuarioService service;

	private FeedbackPanel feedback;

	private NovoUsuarioFormPanel panel;

	private PageHelper pageHelper;

	public NovoUsuarioForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<NovoUsuarioVo>(
				new NovoUsuarioVo()));

		this.pageHelper = pageHelper;

		feedback = new FeedbackPanel("feedbackNou");
		feedback.setOutputMarkupId(true);
		add(feedback);

		criarBtnSalvar();

		inicializar();
	}

	private void criarBtnSalvar() {

		addOrReplace(new AjaxButton("btnReg", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_REGISTRAR))) {

			private static final long serialVersionUID = -2582474580662830738L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				registrar();

				target.add(NovoUsuarioForm.this);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private void inicializar() {

		setModelObject(new NovoUsuarioVo());

		panel = new NovoUsuarioFormPanel("frmPanelNou", pageHelper);
		addOrReplace(panel);
	}

	private void registrar() {

		try {

			service.incluirUsuario(getModelObject(),
					pageHelper.getUrl(getUrlParcialConfirmacao(), getRequest()));

			inicializar();

			info(ResourceUtil
					.recuperaMensagemErro(MensagemSucesso.USUARIO_CADASTRADO_COM_SUCESSO
							.getCodigo()));
			
			//FIXME: Caso a confirmação de e-mail esteja ativada, apresentar pop-up informando para confirmar o e-mail.

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
	 * Obter a URL parcial da classe de confirmação de usuário, que será enviada
	 * por e-mail ao usuário registrado.
	 * 
	 * @return
	 */
	private String getUrlParcialConfirmacao() {

		String url = null;

		final PageParameters pageParameters = new PageParameters();

		pageParameters.add(ConstantesCore.PARAMETRO_GET_USUARIO,
				ConstantesCore.TOKEN);

		pageParameters.add(ConstantesCore.PARAMETRO_GET_CODIGO_VERIFICACAO,
				ConstantesCore.TOKEN);

		try {
			url = URLDecoder.decode(
					(String) urlFor(ConfirmaUsuarioPage.class, pageParameters),
					"UTF-8");
		} catch (Exception e) {
			url = StringUtils.EMPTY;
		}
		return url;
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