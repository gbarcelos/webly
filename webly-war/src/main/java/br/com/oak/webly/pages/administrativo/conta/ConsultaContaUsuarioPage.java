package br.com.oak.webly.pages.administrativo.conta;

import br.com.oak.webly.pages.PaginaExternaPage;
import br.com.oak.webly.pages.componentes.conta.ConsultaContaUsuarioForm;
import br.com.oak.wicket.util.PaginaWeb;

@PaginaWeb(nome = "adminConsultaContaUsuarioPage")
public class ConsultaContaUsuarioPage extends PaginaExternaPage {

	private static final long serialVersionUID = -2063625429439313868L;

	public ConsultaContaUsuarioPage() {

		super("contaUsuario");

		add(new ConsultaContaUsuarioForm("formACCU", getPageHelper()) {

			private static final long serialVersionUID = 5271958954862052095L;

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