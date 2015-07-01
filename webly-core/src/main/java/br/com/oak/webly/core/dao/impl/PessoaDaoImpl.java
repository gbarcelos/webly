package br.com.oak.webly.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import br.com.oak.core.dao.impl.OakDaoImpl;
import br.com.oak.webly.core.dao.PessoaDao;
import br.com.oak.webly.core.model.dbwebly.Pessoa;

@Repository("pessoaDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class PessoaDaoImpl extends OakDaoImpl<Pessoa> implements PessoaDao {

	private static final long serialVersionUID = 1668720134159954661L;

	@Override
	public boolean existePessoaPorNomeOuEmail(final Pessoa pessoa) {

		boolean existePessoa = true;

		final List<Object> parametros = new ArrayList<Object>();
		final StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(*) FROM ");
		hql.append(getPersistentClass().getSimpleName());
		hql.append(" a ");
		hql.append("WHERE 1 = 1 ");

		if (pessoa != null) {

			hql.append("AND (");

			hql.append("a.nome = ? ");
			parametros.add(pessoa.getNome());

			hql.append("OR a.email = ? ");
			parametros.add(pessoa.getEmail());

			hql.append(")");
		}

		final Long qtd = (Long) findSingleResult(hql.toString(),
				parametros.toArray());

		if (qtd != null && qtd.intValue() == 0) {
			existePessoa = false;
		}
		return existePessoa;
	}
}