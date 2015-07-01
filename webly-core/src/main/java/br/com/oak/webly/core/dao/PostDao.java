package br.com.oak.webly.core.dao;

import java.util.List;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.webly.core.model.dbwebly.Post;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.core.vo.filtro.FiltroPostVo;

public interface PostDao extends OakDao<Post> {

	Long recuperaQuantidadeRegistros(FiltroPostVo filtro);

	List<PostVo> recuperaRegistros(FiltroPostVo filtro, Paginacao paginacao);

	List<PostVo> recuperaRegistrosPaginaInicial(FiltroPostVo filtro, Paginacao paginacao);

	List<PostVo> recuperaTopicosRecentes();

	PostVo obterPost(FiltroPostVo filtro);
}