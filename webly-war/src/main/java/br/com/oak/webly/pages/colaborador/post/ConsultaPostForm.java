package br.com.oak.webly.pages.colaborador.post;

import java.util.Date;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import br.com.oak.webly.core.enums.CamposPostEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.service.PostService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.core.vo.filtro.FiltroPostVo;
import br.com.oak.webly.pages.componentes.autor.AutorAutoCompletePanel;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.LabelBehavior;
import br.com.oak.wicket.ui.data.CampoData;
import br.com.oak.wicket.ui.validadores.PeriodoValidator;
import br.com.oak.wicket.util.LoadFocus;

public class ConsultaPostForm extends Form<FiltroPostVo> {

	private static final long serialVersionUID = 3191721393798363265L;

	@SpringBean
	private PostService service;

	private PostProvider provider;

	private FeedbackPanel feedback;

	private PageHelper pageHelper;

	private CampoTexto<String> campoParteTitulo;

	private CampoData<Date> campoInicio;

	private CampoData<Date> campoFim;

	private GridPost grid;

	public ConsultaPostForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<FiltroPostVo>(
				new FiltroPostVo()));

		this.pageHelper = pageHelper;

		feedback = new FeedbackPanel("feedbackCPOST");
		feedback.setOutputMarkupId(true);
		add(feedback);

		provider = new PostProvider(service);

		inicializar();
	}

	private void inicializar() {
		criarCampoParteTitulo();
		criarCampoDataInicio();
		criarCampoDataFim();
		criarCampoAutor();

		criarBtnPesquisar();
		criarBtnNovo();
		criarBtnVoltar();
		criarGrid();

		add(new PeriodoValidator(campoInicio, campoFim));
	}

	private void criarCampoParteTitulo() {

		campoParteTitulo = new CampoTexto<String>("parteTitulo", false);

		campoParteTitulo.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPostEnum.PARTE_TITULO)));

		campoParteTitulo.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER,
				"Informe parte do título ou subtítulo"));
		//FIXME: Alterar para buscar labels do properties

		campoParteTitulo.adicionaLimit(CamposPostEnum.PARTE_TITULO
				.getTamanhoMaximo());

		campoParteTitulo.add(new LabelBehavior());

		addOrReplace(campoParteTitulo);
	}

	private void criarCampoDataInicio() {

		campoInicio = new CampoData<Date>("dateIniPublicacao");

		campoInicio.setLabel(new Model<String>("Data início publicação"));
		//FIXME: Alterar para buscar labels do properties

		campoInicio.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, "dd/mm/aaaa"));

		campoInicio.add(new LabelBehavior());

		add(campoInicio);
	}

	private void criarCampoDataFim() {

		campoFim = new CampoData<Date>("dateFimPublicacao");

		campoFim.setLabel(new Model<String>("Data fim publicação"));
		//FIXME: Alterar para buscar labels do properties

		campoFim.add(new AttributeModifier(ConstantesWeb.ATTRIBUTE_PLACEHOLDER,
				"dd/mm/aaaa"));

		campoFim.add(new LabelBehavior());

		add(campoFim);
	}

	private void criarCampoAutor() {
		add(new AutorAutoCompletePanel("panelAutorAutoComplete", feedback,
				pageHelper));
	}

	private void criarBtnPesquisar() {

		addOrReplace(new AjaxButton("btnPesquisarCPOST", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_CONSULTAR))) {

			private static final long serialVersionUID = 8000208305646884729L;

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

	private void criarBtnNovo() {

		addOrReplace(new AjaxButton("btnNovoCPOST", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_NOVO))) {

			private static final long serialVersionUID = -3200431929905190807L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(EdicaoPostPage.class);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}
		});
	}

	private void criarBtnVoltar() {

		addOrReplace(new Button("btnVoltarCPOST", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_VOLTAR))) {

			private static final long serialVersionUID = 6872556271829604235L;

			@Override
			public void onSubmit() {
				setResponsePage(getApplication().getHomePage());
			}
		});
	}

	private void criarGrid() {

		grid = new GridPost("gridPost", provider) {

			private static final long serialVersionUID = 2241014041362552212L;

			@Override
			protected void comandoAlterar(final PostVo post) {
				setResponsePage(new EdicaoPostPage(post.getCodigo()));
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
				.getFocusScript(campoParteTitulo));
	}
}