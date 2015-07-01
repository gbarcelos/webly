package br.com.oak.webly.pages.componentes.autor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;

import br.com.oak.webly.core.enums.CamposPostEnum;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.util.ConstantesWeb;
import br.com.oak.webly.util.SessionUtil;
import br.com.oak.webly.util.helper.PageHelper;
import br.com.oak.wicket.ui.ajax.AjaxBehavior;
import br.com.oak.wicket.ui.basico.CampoAutoComplete;
import br.com.oak.wicket.ui.behavior.LabelInputAjaxBehavior;

public class AutorAutoCompletePanel extends Panel {

	private static final long serialVersionUID = 1161064502808399017L;

	private static final int NUMERO_LETRAS_AUTO_COMPLETE = 2;

	@SpringBean
	private UsuarioService service;

	private CampoAutoComplete<UsuarioVo> campoAutoComplete;

	private UsuarioVo registroSelecionado;

	private FeedbackPanel feedback;

	private String mensagemRegistroNaoEncontrado;

	private PageHelper pageHelper;

	public AutorAutoCompletePanel(final String idPanel,
			final FeedbackPanel feedback, final PageHelper pageHelper) {

		super(idPanel);
		setOutputMarkupId(true);

		this.feedback = feedback;
		this.pageHelper = pageHelper;

		setMensagemRegistroNaoEncontrado(MensagemErro.NENHUM_REGISTRO_ENCONTRADO_COM_A_DESCRICAO
				.getCodigo());

		criarAutoComplete();
	}

	private void criarAutoComplete() {

		campoAutoComplete = new CampoAutoComplete<UsuarioVo>("autor") {

			private static final long serialVersionUID = -4017445649749673689L;

			@SuppressWarnings("unchecked")
			@Override
			public <C> IConverter<C> getConverter(Class<C> type) {

				if (type == UsuarioVo.class) {
					return (IConverter<C>) new UsuarioConverter() {

						private static final long serialVersionUID = 9140516472174304747L;

						@Override
						public UsuarioVo getUsuarioSelecionado() {
							return registroSelecionado;
						}

						@Override
						public void setUsuarioSelecionado(
								final UsuarioVo usuario) {
							registroSelecionado = usuario;
						}
					};
				} else {
					return super.getConverter(type);
				}
			}

			@Override
			protected Iterator<UsuarioVo> getChoices(final String input) {
				List<UsuarioVo> retorno = null;
				if (input.length() > NUMERO_LETRAS_AUTO_COMPLETE) {
					retorno = service.consultarAutorPorParteNome(input);
				}

				if (retorno == null) {
					retorno = new ArrayList<UsuarioVo>(0);
				}
				return retorno.iterator();
			}
		};
		campoAutoComplete.setOutputMarkupId(true);

		campoAutoComplete.add(new AttributeModifier("onkeyup",
				"Mascara(this, strReplaceChr, 'AlfaChar')"));

		adicionarAjaxCampo();

		setLabelCampo(pageHelper.getStringLabel(CamposPostEnum.AUTOR));

		setPlaceholderCampo(pageHelper
				.getStringLabel(CamposPostEnum.AUTOR_PLACEHOLDER));

		campoAutoComplete.add(new LabelInputAjaxBehavior());

		add(campoAutoComplete);
	}

	private void adicionarAjaxCampo() {

		campoAutoComplete.add(new AjaxBehavior(SessionUtil.identificaAgente()) {

			private static final long serialVersionUID = -2526813450093032025L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {

				if (StringUtils.isNotBlank(campoAutoComplete.getValue())) {

					registroSelecionado = service
							.consultarAutorPorNome(campoAutoComplete.getValue());

					if (registroSelecionado == null) {

						limparCampo();

						error(ResourceUtil.recuperaMensagemErro(
								mensagemRegistroNaoEncontrado,
								campoAutoComplete.getLabel().getObject()));
						target.focusComponent(campoAutoComplete);
					}
					target.add(feedback, campoAutoComplete);
				}
			}
		});
	}

	public void setMensagemRegistroNaoEncontrado(
			final String mensagemConcursoNaoEncontrado) {
		this.mensagemRegistroNaoEncontrado = mensagemConcursoNaoEncontrado;
	}

	public void setLabelCampo(final String labelCampo) {
		campoAutoComplete.setLabel(new Model<String>(labelCampo));
	}

	public void setPlaceholderCampo(final String placeholder) {
		campoAutoComplete.add(new AttributeModifier(
				ConstantesWeb.ATTRIBUTE_PLACEHOLDER, placeholder));
	}

	public void setCampoRequired(boolean required) {
		campoAutoComplete.setRequired(required);
	}

	public void limparCampo() {
		campoAutoComplete.clearInput();
		campoAutoComplete.setModelObject(null);
	}
}