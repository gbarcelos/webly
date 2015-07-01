package br.com.oak.webly.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.oak.core.dao.impl.OakDaoImpl;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.core.enums.StatusRegistroAtivo;
import br.com.oak.core.util.DataUtil;
import br.com.oak.webly.core.dao.PostDao;
import br.com.oak.webly.core.model.dbwebly.Post;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.core.vo.filtro.FiltroPostVo;

@Repository("postDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class PostDaoImpl extends OakDaoImpl<Post> implements PostDao {

	private static final long serialVersionUID = 2572250939337945022L;

	public Long recuperaQuantidadeRegistros(final FiltroPostVo filtro) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT count(*) FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" p ");
		hql.append("WHERE p.statusRegistroAtivo = ? ");
		parametros.add(StatusRegistroAtivo.ATIVO.getValor());

		popularFiltros(filtro, parametros, hql);

		return (Long) findSingleResult(hql.toString(), parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostVo> recuperaRegistros(final FiltroPostVo filtro, final Paginacao paginacao) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.PostVo(");

		hql.append("codigo, titulo, usuario.nomePublico, dataPublicacao");

		hql.append(") FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" p ");
		hql.append("WHERE p.statusRegistroAtivo = ? ");
		parametros.add(StatusRegistroAtivo.ATIVO.getValor());
		
		popularFiltros(filtro, parametros, hql);

		hql.append("ORDER BY p.dataPublicacao DESC ");		

		return (List<PostVo>) find(hql.toString(), paginacao,
				parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostVo> recuperaRegistrosPaginaInicial(final FiltroPostVo filtro, final Paginacao paginacao) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.PostVo(");

		hql.append("titulo, parteUrl, usuario.nomePublico, textoResumoPost, dataPublicacao");

		hql.append(") FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" p ");
		hql.append("WHERE p.statusRegistroAtivo = ? ");
		parametros.add(StatusRegistroAtivo.ATIVO.getValor());

		popularFiltros(filtro, parametros, hql);

		hql.append("ORDER BY p.dataPublicacao DESC ");

		return (List<PostVo>) find(hql.toString(), paginacao, parametros.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostVo> recuperaTopicosRecentes() {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.PostVo(");

		hql.append("titulo, parteUrl");

		hql.append(") FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" p ");
		hql.append("WHERE p.statusRegistroAtivo = ? ");
		parametros.add(StatusRegistroAtivo.ATIVO.getValor());

		hql.append("ORDER BY p.dataPublicacao DESC ");

		return (List<PostVo>) find(hql.toString(), new Paginacao(0, 5),
				parametros.toArray());
	}

	@Override
	public PostVo obterPost(final FiltroPostVo filtro) {

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();

		hql.append("SELECT new ");
		hql.append(ConstantesCore.PACOTE_BASE);
		hql.append(".core.vo.PostVo(");

		hql.append("titulo, subTitulo, usuario.nomePublico, dataPublicacao, textoPost");

		hql.append(") FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" p ");
		hql.append("WHERE p.statusRegistroAtivo = ? ");
		parametros.add(StatusRegistroAtivo.ATIVO.getValor());

		popularFiltros(filtro, parametros, hql);

		return (PostVo) findSingleResult(hql.toString(), parametros.toArray());
	}

	private void popularFiltros(final FiltroPostVo filtro,
			final List<Object> parametros, final StringBuilder hql) {

		if (filtro != null) {

			if (StringUtils.isNotBlank(filtro.getUrl())) {

				hql.append(" AND p.parteUrl = ? ");
				parametros.add(filtro.getUrl());
			}
			
			if (StringUtils.isNotBlank(filtro.getParteTitulo())) {

				hql.append(" AND (");

				hql.append("UPPER(p.titulo) like ? ");

				hql.append("OR UPPER(p.subTitulo) like ?");

				hql.append(") ");

				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getParteTitulo().toUpperCase()
						+ ConstantesCore.PARTE_DESCRICAO);

				parametros.add(ConstantesCore.PARTE_DESCRICAO
						+ filtro.getParteTitulo().toUpperCase()
						+ ConstantesCore.PARTE_DESCRICAO);
			}
			
			if (filtro.getAutor() != null){

				hql.append(" AND p.usuario.codigo = ? ");
				parametros.add(filtro.getAutor().getCodigo());
			}
			
			if (filtro.getDateIniPublicacao() != null && filtro.getDateFimPublicacao() != null){
				
				hql.append(" AND p.dataPublicacao between ?  AND ? ");
				parametros.add(filtro.getDateIniPublicacao());
				parametros.add(DataUtil.cloneWith2359(filtro.getDateFimPublicacao()));
			}
		}
	}
}