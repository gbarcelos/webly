package br.com.oak.webly.core.util;

import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.core.vo.UsuarioLogado;
import br.com.oak.webly.core.vo.UsuarioVo;

public class UsuarioLogadoBuilder {

	private UsuarioLogado usuarioLogado;

	public UsuarioLogadoBuilder(final UsuarioVo usuario) {

		usuarioLogado = new UsuarioLogado();
		usuarioLogado.setCodigo(usuario.getCodigo());
		usuarioLogado.setCodigoPessoa(usuario.getCodigoPessoa());
		usuarioLogado.setNomePessoa(usuario.getNomePessoa());
		usuarioLogado.setEmail(usuario.getEmail());

		usuarioLogado.setNome(usuario.getNome());
		usuarioLogado.setNomePublico(usuario.getNomePublico());
		usuarioLogado.setTipoUsuario(TipoUsuarioEnum
				.getTipoUsuarioByRole(usuario.getRoleUsuario()));

		usuarioLogado.setDataInclusaoExtenso(usuario.getDataInclusao());
	}

	public UsuarioLogado getUsuarioLogado() {
		return usuarioLogado;
	}
}