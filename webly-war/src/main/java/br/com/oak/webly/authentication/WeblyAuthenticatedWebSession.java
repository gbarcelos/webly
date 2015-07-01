package br.com.oak.webly.authentication;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.oak.core.exception.Erro;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.util.helper.ErroHelper;

public class WeblyAuthenticatedWebSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = -2557618928636258019L;

	@SpringBean(name = "authenticationManager")
	private AuthenticationManager authenticationManager;

	public WeblyAuthenticatedWebSession(Request request) {
		super(request);
		injectDependencies();
		ensureDependenciesNotNull();
	}

	private void injectDependencies() {
		Injector.get().inject(this);
	}

	private void ensureDependenciesNotNull() {
		if (authenticationManager == null) {
			throw new IllegalStateException(
					"Sessão administrativa requer um authenticationManager.");
		}
	}

	@Override
	public boolean authenticate(String username, String password) {
		boolean authenticated = false;
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));

			SecurityContextHolder.getContext()
					.setAuthentication(authentication);

			authenticated = authentication.isAuthenticated();

		} catch (AuthenticationException e) {

			WeblyNegocioException cause = (WeblyNegocioException) e.getCause();

			if (cause != null && cause.getErros() != null) {

				for (final Erro erro : cause.getErros()) {
					getFeedbackMessages().error(null,
							new ErroHelper().getMensagemErro(erro));
				}
			}
			authenticated = false;
		}
		return authenticated;
	}

	@Override
	public Roles getRoles() {
		Roles roles = new Roles();
		getRolesIfSignedIn(roles);
		return roles;
	}

	private void getRolesIfSignedIn(Roles roles) {
		if (isSignedIn()) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			addRolesFromAuthentication(roles, authentication);
		}
	}

	private void addRolesFromAuthentication(Roles roles,
			Authentication authentication) {
		for (GrantedAuthority authority : authentication.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
	}
}