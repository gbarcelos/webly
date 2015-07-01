package br.com.oak.webly.pages.administrativo.usuario;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.enums.SimNaoEnum;
import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.core.vo.filtro.FiltroUsuarioVo;
import br.com.oak.webly.pages.componentes.SimNaoRenderer;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.basico.ComboBox;
import br.com.oak.wicket.ui.basico.Radio;
import br.com.oak.wicket.ui.behavior.LabelBehavior;
import br.com.oak.wicket.util.LoadFocus;

public class ConsultaUsuarioForm extends Form<FiltroUsuarioVo> {

	private static final long serialVersionUID = 7104148612788026801L;

	@SpringBean
	private UsuarioService service;

	private UsuarioProvider provider;

	private FeedbackPanel feedback;

	private PageHelper pageHelper;

	private CampoTexto<String> campoParteNome;

	private GridUsuario grid;

	public ConsultaUsuarioForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<FiltroUsuarioVo>(
				new FiltroUsuarioVo()));

		this.pageHelper = pageHelper;

		feedback = new FeedbackPanel("feedbackCOUSR");
		feedback.setOutputMarkupId(true);
		add(feedback);

		provider = new UsuarioProvider(service);

		inicializar();
	}

	private void inicializar() {
		criarCampoParteNome();
		criarCampoEmail();
		criarCampoTipoUsuario();
		criarCampoAtivoInativo();

		criarBtnPesquisar();
		criarBtnVoltar();
		criarGrid();
	}

	private void criarCampoParteNome() {

		campoParteNome = new CampoTexto<String>("parteNome", false);

		campoParteNome.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.PARTE_NOME)));

		campoParteNome.adicionaLimit(CamposUsuarioEnum.PARTE_NOME
				.getTamanhoMaximo());

		campoParteNome.add(new LabelBehavior());

		addOrReplace(campoParteNome);
	}

	private void criarCampoEmail() {

		final CampoTexto<String> campoEmail = new CampoTexto<String>("email",
				false);

		campoEmail.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.EMAIL)));

		campoEmail.adicionaLimit(CamposUsuarioEnum.EMAIL.getTamanhoMaximo());

		campoEmail.add(new LabelBehavior());

		addOrReplace(campoEmail);
	}

	private void criarCampoTipoUsuario() {

		final ComboBox<TipoUsuarioEnum> comboTipoUsuario = new ComboBox<TipoUsuarioEnum>(
				"tipoUsuario", Arrays.asList(TipoUsuarioEnum.values()),
				new ChoiceRenderer<TipoUsuarioEnum>("descricao", "codigo"));

		comboTipoUsuario.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.TIPO_USUARIO)));

		comboTipoUsuario.add(new LabelBehavior());

		add(comboTipoUsuario);
	}

	private void criarCampoAtivoInativo() {

		final Radio<SimNaoEnum> radio = new Radio<SimNaoEnum>(
				"statusRegistroAtivo", Arrays.asList(SimNaoEnum.values()),
				new SimNaoRenderer(), true);

		radio.setOutputMarkupId(true);

		radio.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposUsuarioEnum.USUARIO_ATIVO_INATIVO)));

		radio.add(new LabelBehavior());

		add(radio);
	}

	private void criarBtnPesquisar() {

		addOrReplace(new AjaxButton("btnPesquisarCOUSR", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_CONSULTAR))) {

			private static final long serialVersionUID = -8312076247125068786L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				pesquisar();
				target.add(feedback, grid);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private void criarBtnVoltar() {

		addOrReplace(new Button("btnVoltarCOUSR", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_VOLTAR))) {

			private static final long serialVersionUID = 7811500063572655050L;

			@Override
			public void onSubmit() {
				setResponsePage(getApplication().getHomePage());
			}
		});
	}

	private void criarGrid() {

		grid = new GridUsuario("gridUsuario", provider) {

			private static final long serialVersionUID = -3496017953527982104L;

			@Override
			protected void comandoAlterar(final UsuarioVo usuario) {
				final TipoUsuarioEnum tipoUsuario = TipoUsuarioEnum
						.getTipoUsuarioByDescricao(usuario
								.getDescricaoTipoUsuario());

				if (!TipoUsuarioEnum.SUPER_ADMINISTRATIVO.equals(tipoUsuario)) {
					setResponsePage(new UsuarioPage(usuario.getCodigo()));
				}
			}
		};
		add(grid);
	}

	private void pesquisar() {

		boolean visualizar = true;

		provider.setFiltro(getModelObject());

		if (provider.size() == 0) {

			visualizar = false;
			error(ResourceUtil
					.recuperaMensagemErro(MensagemErro.NENHUM_REGISTRO
							.getCodigo()));
		}
		grid.exibirLista(visualizar);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.renderOnLoadJavaScript(LoadFocus
				.getFocusScript(campoParteNome));
	}
}