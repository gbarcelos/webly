package br.com.oak.webly.pages.visitante.conta;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.ConsultaContaUsuarioForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "visitConsultaContaUsuarioPage")
public class ConsultaContaUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = 5574630587314980972L;

	public ConsultaContaUsuarioPage() {

		super("contaUsuario");

		add(new ConsultaContaUsuarioForm("formVCCU", getPageHelper()) {

			private static final long serialVersionUID = 7942576781610466393L;

			@Override
			protected void alterarDadosUsuario(final Long codigo) {
				setResponsePage(new AlteraUsuarioPage(codigo));
			}

			@Override
			protected void alterarDadosPessoais(final Long codigo) {
				setResponsePage(new AlteraDadosPessoaisPage(codigo));
			}
		});
	}
}