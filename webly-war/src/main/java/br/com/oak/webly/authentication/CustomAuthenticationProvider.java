package br.com.oak.webly.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import br.com.oak.core.exception.OakException;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ResourceUtil;
import br.com.oak.webly.core.util.UsuarioLogadoBuilder;
import br.com.oak.webly.core.vo.LoginVo;
import br.com.oak.webly.core.vo.UsuarioVo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UsuarioService service;

	@Override
	public Authentication authenticate(final Authentication authentication)
			throws AuthenticationException {

		try {

			final String senha = (String) authentication.getCredentials();

			final UsuarioVo usuario = service
					.recuperarUsuarioParaLogin(new LoginVo(authentication
							.getName(), senha));

			final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl(usuario.getRoleUsuario()));

			return new UsernamePasswordAuthenticationToken(
					new UsuarioLogadoBuilder(usuario).getUsuarioLogado(),
					senha, authorities);

		} catch (final WeblyNegocioException e) {
			throw new BadCredentialsException(e.getMessage(), e);

		} catch (final OakException e) {

			throw new BadCredentialsException(
					ResourceUtil.recuperaMensagemErro(MensagemErro.ERRO_INESPERADO
							.getCodigo()));
		} catch (final Exception e) {

			throw new BadCredentialsException(
					ResourceUtil.recuperaMensagemErro(MensagemErro.ERRO_INESPERADO
							.getCodigo()));
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return true;
	}
}