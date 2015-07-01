package br.com.oak.webly.core.dao;

import java.util.List;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.webly.core.model.dbwebly.Usuario;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.core.vo.filtro.FiltroUsuarioVo;

public interface UsuarioDao extends OakDao<Usuario> {

	UsuarioVo recuperarUsuarioParaLogin(Usuario usuario);

	boolean existeUsuarioParaLogin(Usuario usuario);

	Long recuperaQuantidadeRegistros(FiltroUsuarioVo filtro);

	List<UsuarioVo> recuperaRegistros(FiltroUsuarioVo filtro,
			Paginacao paginacao);
}