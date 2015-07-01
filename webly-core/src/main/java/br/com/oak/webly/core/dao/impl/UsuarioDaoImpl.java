package br.com.oak.webly.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.oak.core.dao.impl.OakDaoImpl;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.webly.core.dao.UsuarioDao;
import br.com.oak.webly.core.enums.SimNaoEnum;
import br.com.oak.webly.core.enums.SituacaoUsuarioEnum;
import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.core.model.dbwebly.Usuario;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.core.vo.filtro.FiltroUsuarioVo;

@Repository("usuarioDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class UsuarioDaoImpl extends OakDaoImpl<Usuario> implements UsuarioDao {

	private static final long serialVersionUID = -3607836466193655930L;

	@Override
	public Long recuperaQuantidadeRegistros(final FiltroUsuarioVo filtro) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT count(*) FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" u ");

		popularJoins(filtro, hql);

		hql.append("WHERE 1 = 1 ");

		popularFiltros(filtro, parametros, hql);
		return (Long) findSingleResult(hql.toString(), parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioVo> recuperaRegistros(final FiltroUsuarioVo filtro,
			final Paginacao paginacao) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.UsuarioVo(");

		hql.append("u.codigo,");

		hql.append("p.nome,");

		hql.append("u.nome,");

		hql.append("p.email,");

		hql.append("t.descricao,");

		hql.append("u.dataInclusao,");

		hql.append("u.dataAlteracao,");

		hql.append("u.statusRegistroAtivo");

		hql.append(") FROM ");

		hql.append(getPersistentClass().getSimpleName());
		hql.append(" u ");

		hql.append("INNER JOIN u.pessoa p ");
		hql.append("INNER JOIN u.tipoUsuario t ");

		hql.append("WHERE 1 = 1 ");

		popularFiltros(filtro, parametros, hql);
		popularOrdenacao(filtro, hql);

		return (List<UsuarioVo>) find(hql.toString(), paginacao,
				parametros.toArray());
	}

	@Override
	public UsuarioVo recuperarUsuarioParaLogin(final Usuario usuario) {// ***

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.UsuarioVo(");

		hql.append("u.codigo,");

		hql.append("p.codigo,");

		hql.append("p.nome,");

		hql.append("u.nome,");

		hql.append("u.nomePublico,");

		hql.append("p.email,");

		hql.append("t.role,");

		hql.append("u.dataInclusao");

		hql.append(") FROM ");

		hql.append(getPersistentClass().getSimpleName());
		hql.append(" u ");

		hql.append("INNER JOIN u.pessoa p ");
		hql.append("INNER JOIN u.tipoUsuario t ");
		hql.append("WHERE 1 = 1 ");

		popularFiltrosUsuarioParaLogin(usuario, parametros, hql);

		return (UsuarioVo) findSingleResult(hql.toString(),
				parametros.toArray());
	}

	@Override
	public boolean existeUsuarioParaLogin(final Usuario usuario) {

		boolean existeUsuario = false;

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(*) FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" u ");
		hql.append("WHERE 1 = 1 ");

		popularFiltrosUsuarioParaLogin(usuario, parametros, hql);

		final Long qtd = (Long) findSingleResult(hql.toString(),
				parametros.toArray());

		if (qtd != null && qtd.intValue() > 0) {
			existeUsuario = true;
		}
		return existeUsuario;
	}

	public void popularFiltrosUsuarioParaLogin(final Usuario usuario,
			final List<Object> parametros, final StringBuilder hql) {

		hql.append(" AND u.statusRegistroAtivo = ? ");
		parametros.add(SimNaoEnum.SIM);

		hql.append("AND u.situacaoUsuario.codigo = ? ");
		parametros.add(SituacaoUsuarioEnum.CONFIRMADO.getCodigo());

		if (usuario != null) {

			if (StringUtils.isNotBlank(usuario.getSenha())) {
				hql.append("AND u.senha = ? ");
				parametros.add(usuario.getSenha());
			}

			if (StringUtils.isNotBlank(usuario.getNome())) {
				hql.append("AND UPPER(u.nome) = ? ");
				parametros.add(usuario.getNome().toUpperCase());
			}

			if (usuario.getPessoa() != null
					&& StringUtils.isNotBlank(usuario.getPessoa().getEmail())) {
				hql.append("AND p.email = ? ");
				parametros.add(usuario.getPessoa().getEmail());
			}
		}
	}

	private void popularJoins(final FiltroUsuarioVo filtro,
			final StringBuilder hql) {

		if (filtro != null) {

			if (StringUtils.isNotBlank(filtro.getParteNome())
					|| StringUtils.isNotBlank(filtro.getEmail())) {
				hql.append("INNER JOIN u.pessoa p ");
			}
		}
	}

	private void popularFiltros(final FiltroUsuarioVo filtro,
			final List<Object> parametros, final StringBuilder hql) {

		if (filtro != null) {

			if (filtro.getStatusRegistroAtivo() != null) {

				hql.append("AND u.statusRegistroAtivo = ? ");
				parametros.add(filtro.getStatusRegistroAtivo());
			}

			if (StringUtils.isNotBlank(filtro.getParteNome())) {

				hql.append("AND (");

				hql.append("UPPER(p.nome) like ? ");

				hql.append("OR UPPER(u.nome) like ?");

				hql.append(") ");

				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getParteNome().toUpperCase()
						+ ConstantesCore.PARTE_DESCRICAO);

				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getParteNome().toUpperCase()
						+ ConstantesCore.PARTE_DESCRICAO);
			}

			if (StringUtils.isNotBlank(filtro.getParteNomeAutor())) {

				hql.append("AND UPPER(u.nome) like ? ");
				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getParteNomeAutor().toUpperCase()
						+ ConstantesCore.PARTE_DESCRICAO);

				hql.append("AND u.tipoUsuario.codigo <> ? ");
				parametros.add(TipoUsuarioEnum.VISITANTE.getCodigo());
			}

			if (StringUtils.isNotBlank(filtro.getNomeAutor())) {

				hql.append("AND UPPER(u.nome) = ? ");
				parametros.add(filtro.getNomeAutor().toUpperCase());

				hql.append("AND u.tipoUsuario.codigo <> ? ");
				parametros.add(TipoUsuarioEnum.VISITANTE.getCodigo());
			}

			if (StringUtils.isNotBlank(filtro.getEmail())) {
				hql.append("AND p.email like ? ");
				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getEmail() + ConstantesCore.PARTE_DESCRICAO);
			}

			if (filtro.getTipoUsuario() != null) {
				hql.append("AND u.tipoUsuario.codigo = ? ");
				parametros.add(filtro.getTipoUsuario().getCodigo());
			}
		}
	}

	private void popularOrdenacao(final FiltroUsuarioVo filtro,
			final StringBuilder hql) {
		hql.append("ORDER BY ");
		if (filtro.getParametroDeOrdenacao().getCampo().equals("nomePessoa")) {
			hql.append("p.nome");

		} else if (filtro.getParametroDeOrdenacao().getCampo().equals("nome")) {
			hql.append("u.nome");

		} else if (filtro.getParametroDeOrdenacao().getCampo().equals("email")) {
			hql.append("p.email");
		} else {
			hql.append("t.descricao");
		}
		hql.append(ConstantesCore.ESPACO_BRANCO);
		hql.append(filtro.getParametroDeOrdenacao().getOrdenacao());
	}
}