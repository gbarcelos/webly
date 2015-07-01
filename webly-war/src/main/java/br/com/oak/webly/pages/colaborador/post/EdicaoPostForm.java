package br.com.oak.webly.pages.colaborador.post;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.CollectionUtils;

import br.com.oak.core.exception.Erro;
import br.com.oak.core.exception.OakException;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.mensagem.MensagemSucesso;
import br.com.oak.webly.core.model.dbwebly.Post;
import br.com.oak.webly.core.model.dbwebly.Usuario;
import br.com.oak.webly.core.service.PostService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.UsuarioLogado;
import br.com.oak.webly.util.AuthenticationUtils;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.helper.ErroHelper;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.util.application.AcaoResponsePageEnum;

public class EdicaoPostForm extends Form<Post> {

	private static final long serialVersionUID = 3082993720784055415L;

	@SpringBean
	private PostService service;

	private AcaoResponsePageEnum acao;

	private FeedbackPanel feedBack;

	private PageHelper pageHelper;

	private EdicaoPostFormPanel panel;

	private Post post;

	public EdicaoPostForm(final String idForm, final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<Post>(new Post()));

		this.pageHelper = pageHelper;
		this.acao = AcaoResponsePageEnum.INSERIR;

		inicializar();
	}

	public EdicaoPostForm(final String idForm, final Long codigo,
			final PageHelper pageHelper) {

		super(idForm, new CompoundPropertyModel<Post>(new Post()));

		this.pageHelper = pageHelper;
		this.acao = AcaoResponsePageEnum.ALTERAR;

		post = service.obter(codigo);

		inicializar();
	}

	private void inicializar() {

		feedBack = new FeedbackPanel("feedbackEPOST");
		feedBack.setOutputMarkupId(true);
		addOrReplace(feedBack);

		criarBtnGravar();
		criarBtnVoltar();

		inicializarTela();
	}

	private void criarBtnGravar() {

		Button btn = new Button("btnGravarEPOST", new Model<String>(
				pageHelper.getStringLabel(ConstantesWeb.LABEL_BTN_SALVAR))) {

			private static final long serialVersionUID = -4511070825966461294L;

			@Override
			public void onSubmit() {
				salvar();
			}
		};

		addOrReplace(btn);
	}

	private void criarBtnVoltar() {

		final AjaxButton btnVoltar = new AjaxButton("btnVoltarEPOST",
				new Model<String>(pageHelper
						.getStringLabel(ConstantesWeb.LABEL_BTN_VOLTAR))) {

			private static final long serialVersionUID = -4929364955496443298L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				setResponsePage(ConsultaPostPage.class);
			}

			@Override
			protected void onError(final AjaxRequestTarget target,
					final Form<?> form) {
				target.add(feedBack);
			}
		};
		btnVoltar.setDefaultFormProcessing(false);

		addOrReplace(btnVoltar);
	}

	private void inicializarTela() {

		setOutputMarkupId(true);
		setMultiPart(true);

		if (AcaoResponsePageEnum.INSERIR.equals(acao)) {
			setModelObject(new Post());
		} else {
			setModelObject(post);
		}

		panel = new EdicaoPostFormPanel("formPanelEPOST", pageHelper, post);
		addOrReplace(panel);
	}

	private void salvar() {

		try {

			final Post post = getModelObject();

			popularPost(post);
			popularResumoPost(post);

			service.incluirouAlterar(post);

			inicializar();

			info(ResourceUtil
					.recuperaMensagemErro(MensagemSucesso.OPERACAO_COM_SUCESSO
							.getCodigo()));

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

	private void popularPost(final Post post) {

		final UsuarioLogado usuarioLogado = AuthenticationUtils
				.getUsuarioLogado();

		post.setUsuario(new Usuario(usuarioLogado.getCodigo()));

		post.setTextoPost(panel.getCampoPost().getValue());
	}

	private void popularResumoPost(final Post post) {
		//FIXME: Alterar para tratar o texto na camada de serviço

		final Document document = Jsoup.parse(StringEscapeUtils
				.unescapeHtml(post.getTextoPost()));

		final String textoPost = document.text();

		if (textoPost.length() > 600) {
			post.setTextoResumoPost(textoPost.substring(0, 600));
		} else {
			post.setTextoResumoPost(textoPost);
		}
	}
}