package br.com.oak.webly.pages.publico.quemsomos;

import org.apache.wicket.markup.html.form.Form;

import br.com.oak.webly.util.helper.PageHelper;

public class QuemSomosForm extends Form<Object> {

	private static final long serialVersionUID = -6401368835957034462L;

	public QuemSomosForm(final String idForm, final PageHelper pageHelper) {
		super(idForm);

		add(new QuemSomosFormPanel("formPanelQuem", pageHelper));
	}
}