package br.com.oak.webly.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.enums.StatusRegistroAtivo;
import br.com.oak.core.exception.Erro;
import br.com.oak.core.service.EntidadeService;
import br.com.oak.webly.core.dao.PessoaDao;
import br.com.oak.webly.core.enums.CamposPessoaEnum;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.model.dbwebly.Pessoa;
import br.com.oak.webly.core.util.validadores.UtilValidadorEmail;
import br.com.oak.webly.core.util.validadores.UtilValidadorNomePessoa;

@Service("pessoaService")
public class PessoaService extends EntidadeService<Pessoa> {

	@Autowired
	private PessoaDao dao;

	@Override
	protected OakDao<Pessoa> getDao() {
		return dao;
	}

	@Override
	public void incluir(final Pessoa pessoa) {

		pessoa.setStatusRegistroAtivo(StatusRegistroAtivo.ATIVO.getValor());
		super.incluir(pessoa);
	}

	public void validarPessoa(final Pessoa pessoa) {
		validarObrigatorio(pessoa);
		validarDadoInvalido(pessoa);

		boolean existePessoa = dao.existePessoaPorNomeOuEmail(pessoa);

		if (existePessoa) {
			throw new WeblyNegocioException(new Erro(
					MensagemErro.USUARIO_JA_EXISTE.getCodigo()));
		}
	}

	private void validarObrigatorio(final Pessoa pessoa) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (StringUtils.isBlank(pessoa.getNome())) {
			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposPessoaEnum.NOME));
		}

		if (StringUtils.isBlank(pessoa.getEmail())) {
			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposPessoaEnum.EMAIL));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}

	private void validarDadoInvalido(final Pessoa pessoa) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (UtilValidadorNomePessoa.isTamanhoNomePessoaInvalido(pessoa
				.getNome())) {
			erros.add(new Erro(MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE
					.getCodigo(), CamposPessoaEnum.NOME, CamposPessoaEnum.NOME
					.getTamanhoMinimo(), CamposPessoaEnum.NOME
					.getTamanhoMaximo()));
		}

		if (UtilValidadorEmail.isTamanhoEmailInvalido(pessoa.getEmail())) {

			erros.add(new Erro(MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO
					.getCodigo(), CamposPessoaEnum.EMAIL,
					CamposPessoaEnum.EMAIL.getTamanhoMaximo()));

		} else if (UtilValidadorEmail.isEmailInvalido(pessoa.getEmail())) {

			erros.add(new Erro(MensagemErro.EMAIL_INVALIDO.getCodigo(),
					CamposPessoaEnum.EMAIL));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}
}