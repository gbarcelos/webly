package br.com.oak.webly.pages.publico.login;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import br.com.oak.webly.core.vo.LoginVo;
import br.com.oak.webly.menu.FabricaMenu;
import br.com.oak.webly.menu.MenuUsuarioLogado;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.SessionKey;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.LoadFocus;

public class LoginForm extends Form<LoginVo> {

	private static final long serialVersionUID = 9087794886914581833L;

	private FeedbackPanel feedback;

	private LoginFormPanel panel;

	private PageHelper pageHelper;

	public LoginForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<LoginVo>(new LoginVo()));

		this.pageHelper = pageHelper;

		feedback = new FeedbackPanel("feedbacklg");
		feedback.setOutputMarkupId(true);
		add(feedback);

		panel = new LoginFormPanel("frmPanelLg", pageHelper);
		add(panel);

		criarBtnLogin();
	}

	private void criarBtnLogin() {

		addOrReplace(new AjaxButton("btnLg", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_ENTRAR))) {

			private static final long serialVersionUID = 6737659749280593074L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

				login();

				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private void login() {

		final LoginVo loginVo = getModelObject();

		if (AuthenticatedWebSession.get().signIn(
				loginVo.getNomeUsuarioOuEmail(), loginVo.getPassword())) {

			Session.get().setAttribute(
					SessionKey.MENU_USUARIO_LOGADO.getKey(),
					new MenuUsuarioLogado(new FabricaMenu()
							.criarMenuPorTipoDeUsuario(AuthenticationUtils
									.getTipoUsuarioEnum())));

			setResponsePage(getApplication().getHomePage());
		}
	}

	/**
	 * Direciona o foco para o primeiro campo do formulário
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnLoadJavaScript(LoadFocus.getFocusScript(panel
				.getCampoNomeUsuarioOuEmail()));
	}
}