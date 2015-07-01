package br.com.oak.webly.core.dao;

import br.com.oak.core.dao.OakDao;
import br.com.oak.webly.core.model.dbwebly.Pessoa;

public interface PessoaDao extends OakDao<Pessoa> {

	boolean existePessoaPorNomeOuEmail(Pessoa pessoa);
}