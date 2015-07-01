package br.com.oak.webly.pages.componentes;

import org.apache.wicket.markup.html.form.IChoiceRenderer;

import br.com.oak.webly.core.enums.SimNaoEnum;

public class SimNaoRenderer implements IChoiceRenderer<SimNaoEnum> {

	private static final long serialVersionUID = 7062615170582329488L;

	@Override
	public Object getDisplayValue(final SimNaoEnum object) {
		return object.getDescricao();
	}

	@Override
	public String getIdValue(final SimNaoEnum object, int index) {
		return object.getDescricao();
	}
}