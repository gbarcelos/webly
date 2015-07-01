package br.com.oak.webly.pages.colaborador.post;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.oak.webly.core.enums.CamposPostEnum;
import br.com.oak.webly.core.model.dbwebly.Post;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.basico.CampoTexto;
import br.com.oak.wicket.ui.behavior.LabelBehavior;

import com.google.code.jqwicket.ui.ckeditor.CKEditorOptions;
import com.google.code.jqwicket.ui.ckeditor.CKEditorTextArea;

public class EdicaoPostFormPanel extends Panel {

	private static final long serialVersionUID = -6014686451561737253L;

	private PageHelper pageHelper;

	@SuppressWarnings("unused")
	private String textoPost;

	private CKEditorTextArea<String> campoPost;

	public EdicaoPostFormPanel(final String idPanel,
			final PageHelper pageHelper, final Post post) {

		super(idPanel);
		setOutputMarkupId(true);

		this.pageHelper = pageHelper;

		if (post != null) {
			textoPost = StringEscapeUtils.unescapeHtml(post.getTextoPost());
		}

		inicializar();
	}

	private void inicializar() {
		criarCampoTitulo();
		criarCampoSubTitulo();
		criarCampoPost();
	}

	private void criarCampoTitulo() {

		final CampoTexto<String> campoTitulo = new CampoTexto<String>("titulo",
				true);

		campoTitulo.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPostEnum.TITULO)));

		campoTitulo.adicionaLimit(CamposPostEnum.TITULO.getTamanhoMaximo());

		campoTitulo.add(new LabelBehavior());

		addOrReplace(campoTitulo);
	}

	private void criarCampoSubTitulo() {

		final CampoTexto<String> campoSubTitulo = new CampoTexto<String>(
				"subTitulo", false);

		campoSubTitulo.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPostEnum.SUB_TITULO)));

		campoSubTitulo.adicionaLimit(CamposPostEnum.SUB_TITULO
				.getTamanhoMaximo());

		campoSubTitulo.add(new LabelBehavior());

		addOrReplace(campoSubTitulo);
	}

	private void criarCampoPost() {

		final CharSequence[][] toolbarOpcoes = new CharSequence[][] {
				{ "Undo", "Redo", "Bold", "Italic", "Underline", "JustifyLeft",
						"JustifyCenter", "JustifyRight", "JustifyBlock",
						"NumberedList", "BulletedList", "TextColor", "BGColor",
						"Table", "Cut", "Copy", "Paste", "PasteText", "Font",
						"FontSize", "-", "Link", "Unlink" }, { "UIColor" } };

		final CKEditorOptions opcoes = new CKEditorOptions();

		opcoes.width(620).resize_maxWidth(620).height(400)
				.dialog_backgroundCoverColor("rgb(0, 0, 0)").language("pt")
				.toolbar(toolbarOpcoes);

		campoPost = new CKEditorTextArea<String>("textoPost", opcoes,
				new PropertyModel<String>(this, "textoPost"));

		campoPost.setLabel(new Model<String>(pageHelper
				.getStringLabel(CamposPostEnum.POST)));

		campoPost.setRequired(true);

		campoPost.setOutputMarkupId(true);

		campoPost.add(new LabelBehavior());

		addOrReplace(campoPost);
	}

	public CKEditorTextArea<String> getCampoPost() {
		return campoPost;
	}
}