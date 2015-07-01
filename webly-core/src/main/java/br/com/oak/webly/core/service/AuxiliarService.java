package br.com.oak.webly.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.oak.core.dao.Dao;
import br.com.oak.core.enums.StatusRegistroAtivo;
import br.com.oak.webly.core.model.dbwebly.TipoUsuario;

@Service("auxiliarService")
public class AuxiliarService implements Serializable {

	private static final long serialVersionUID = 3333044759674842838L;

	@Autowired
	@Qualifier("dao")
	private Dao<TipoUsuario> tipoUsuarioDao;

	@SuppressWarnings("unchecked")
	public List<TipoUsuario> listarTiposDeUsuarioAtivos() {

		return (List<TipoUsuario>) tipoUsuarioDao.find(
				"FROM TipoUsuario WHERE statusRegistroAtivo = ?",
				StatusRegistroAtivo.ATIVO.getValor());
	}
}