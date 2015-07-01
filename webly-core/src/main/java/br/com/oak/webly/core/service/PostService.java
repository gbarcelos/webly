package br.com.oak.webly.core.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.core.enums.StatusRegistroAtivo;
import br.com.oak.core.service.EntidadeService;
import br.com.oak.core.util.StringUtil;
import br.com.oak.webly.core.dao.PostDao;
import br.com.oak.webly.core.model.dbwebly.Post;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.core.vo.filtro.FiltroPostVo;

@Service("postService")
public class PostService extends EntidadeService<Post> {

	@Autowired
	private PostDao dao;

	@Override
	protected OakDao<Post> getDao() {
		return dao;
	}

	public Long recuperaQuantidadeRegistros(final FiltroPostVo filtro) {
		return dao.recuperaQuantidadeRegistros(filtro);
	}

	public List<PostVo> recuperaRegistros(final FiltroPostVo filtro,
			final Paginacao paginacao) {
		return dao.recuperaRegistros(filtro, paginacao);
	}

	public List<PostVo> recuperaRegistrosPaginaInicial(
			final FiltroPostVo filtro, final Paginacao paginacao) {
		return dao.recuperaRegistrosPaginaInicial(filtro, paginacao);
	}

	public List<PostVo> listarTopicosRecentes() {

		List<PostVo> recentes = null;

		final Long qtd = dao.recuperaQuantidadeRegistros(new FiltroPostVo());

		if (qtd != null && qtd.intValue() > 0) {
			recentes = dao.recuperaTopicosRecentes();
		}
		return recentes;
	}

	public PostVo obterPostByUrl(final String url) {

		PostVo post = null;

		final FiltroPostVo filtro = new FiltroPostVo(url);

		final Long qtd = dao.recuperaQuantidadeRegistros(filtro);

		if (qtd != null && qtd.intValue() > 0) {
			post = dao.obterPost(filtro);
		}
		return post;
	}

	@Transactional
	public void incluirouAlterar(final Post post) {

		popularUrl(post);

		if (post.getCodigo() == null) {

			incluir(post);

		} else {
			super.alterar(post);
		}
	}

	@Override
	public void incluir(final Post post) {

		post.setStatusRegistroAtivo(StatusRegistroAtivo.ATIVO.getValor());
		post.setDataCriacao(Calendar.getInstance().getTime());

		//FIXME: validar se titulo ja existe, validar se URL gerada já existe.

		// FIXME: temporariamente(até implementação do status do post)
		post.setDataPublicacao(Calendar.getInstance().getTime());

		super.incluir(post);
	}

	private void popularUrl(final Post post) {

		String url = post.getTitulo().toLowerCase();

		url = StringUtil.retiraCaracteresEspeciais(url);
		url = StringUtil.retiraNumero(url);
		url = StringUtil.retiraAcentuacao(url);
		url = StringUtil.retirarEspacoExcedente(url);

		post.setParteUrl(url.replaceAll(" ", "-"));
	}
}