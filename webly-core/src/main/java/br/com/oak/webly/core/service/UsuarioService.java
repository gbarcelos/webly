package br.com.oak.webly.core.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.oak.core.dao.OakDao;
import br.com.oak.core.entidade.Paginacao;
import br.com.oak.core.entidade.ParametroDeOrdenacao;
import br.com.oak.core.exception.Erro;
import br.com.oak.core.service.EntidadeService;
import br.com.oak.core.util.PasswordUtil;
import br.com.oak.core.util.UUIDChars;
import br.com.oak.webly.core.dao.UsuarioDao;
import br.com.oak.webly.core.enums.CamposUsuarioEnum;
import br.com.oak.webly.core.enums.SimNaoEnum;
import br.com.oak.webly.core.enums.SituacaoUsuarioEnum;
import br.com.oak.webly.core.enums.TipoUsuarioEnum;
import br.com.oak.webly.core.exception.WeblyNegocioException;
import br.com.oak.webly.core.mensagem.MensagemErro;
import br.com.oak.webly.core.model.dbwebly.Pessoa;
import br.com.oak.webly.core.model.dbwebly.SituacaoUsuario;
import br.com.oak.webly.core.model.dbwebly.TipoUsuario;
import br.com.oak.webly.core.model.dbwebly.Usuario;
import br.com.oak.webly.core.util.ConstantesCore;
import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.util.validadores.UtilValidadorCodigoVerificacao;
import br.com.oak.webly.core.util.validadores.UtilValidadorPassword;
import br.com.oak.webly.core.util.validadores.UtilValidatorNomeUsuarioEmail;
import br.com.oak.webly.core.vo.ConfirmaUsuarioVo;
import br.com.oak.webly.core.vo.LoginVo;
import br.com.oak.webly.core.vo.MensagemVo;
import br.com.oak.webly.core.vo.NovoUsuarioVo;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.core.vo.filtro.FiltroUsuarioVo;

@Service("usuarioService")
public class UsuarioService extends EntidadeService<Usuario> {

	@Autowired
	private UsuarioDao dao;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private String emailDestinatarioEndereco;

	@Override
	protected OakDao<Usuario> getDao() {
		return dao;
	}

	public Long recuperaQuantidadeRegistros(final FiltroUsuarioVo filtro) {
		return dao.recuperaQuantidadeRegistros(filtro);
	}

	public List<UsuarioVo> recuperaRegistros(final FiltroUsuarioVo filtro,
			final Paginacao paginacao) {
		return dao.recuperaRegistros(filtro, paginacao);
	}

	public UsuarioVo recuperarUsuarioParaLogin(final LoginVo loginVo) {

		validarUsuarioParaAutenticacao(loginVo);

		return recuperarUsuarioParaLogin(criarUsuario(loginVo));
	}

	private UsuarioVo recuperarUsuarioParaLogin(final Usuario usuario) {

		popularPasswordHash(usuario);

		if (!dao.existeUsuarioParaLogin(usuario)) {

			CamposUsuarioEnum campo = null;

			if (StringUtils.isNotBlank(usuario.getNome())) {

				campo = CamposUsuarioEnum.NOME_USUARIO;

			} else if (StringUtils.isNotBlank(usuario.getPessoa().getEmail())) {

				campo = CamposUsuarioEnum.EMAIL;
			}
			throw new WeblyNegocioException(new Erro(
					MensagemErro.NOME_USUARIO_EOU_SENHA_INCORRETOS.getCodigo(),
					campo));
		}
		return dao.recuperarUsuarioParaLogin(usuario);
	}

	public List<UsuarioVo> consultarAutorPorParteNome(final String parteNome) {

		List<UsuarioVo> usuarios = null;

		final FiltroUsuarioVo filtro = new FiltroUsuarioVo();
		filtro.setParteNomeAutor(parteNome);
		filtro.setParametroDeOrdenacao(new ParametroDeOrdenacao("nome", "ASC"));

		final Long qtd = dao.recuperaQuantidadeRegistros(filtro);

		if (qtd != null && qtd.intValue() > 0) {

			usuarios = dao.recuperaRegistros(filtro, new Paginacao());
		}
		return usuarios;
	}

	public UsuarioVo consultarAutorPorNome(final String nome) {

		UsuarioVo usuario = null;

		final FiltroUsuarioVo filtro = new FiltroUsuarioVo();
		filtro.setNomeAutor(nome);
		filtro.setParametroDeOrdenacao(new ParametroDeOrdenacao("nome", "ASC"));

		final Long qtd = dao.recuperaQuantidadeRegistros(filtro);

		if (qtd != null && qtd.intValue() > 0) {

			final List<UsuarioVo> lista = dao.recuperaRegistros(filtro,
					new Paginacao());

			usuario = lista.get(0);
		}
		return usuario;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void incluirUsuario(final NovoUsuarioVo novoUsuarioVo,
			final String urlConfirmacao) {

		final Pessoa pessoa = new Pessoa();
		final Usuario usuario = new Usuario();

		pessoa.setNome(novoUsuarioVo.getNome());
		pessoa.setEmail(novoUsuarioVo.getEmail());
		usuario.setSenha(novoUsuarioVo.getPassword());

		pessoaService.validarPessoa(pessoa);
		validarUsuario(usuario);

		pessoaService.incluir(pessoa);
		usuario.setPessoa(pessoa);
		incluir(usuario, urlConfirmacao);
	}

	private void incluir(final Usuario usuario, final String urlConfirmacao) {

		try {

			popularNomeUsuario(usuario);
			popularNomePublico(usuario);
			popularPasswordHash(usuario);
			popularSituacaoUsuario(usuario);
			popularCodigoVerificacao(usuario);

			usuario.setTipoUsuario(new TipoUsuario(TipoUsuarioEnum.VISITANTE
					.getCodigo()));

			usuario.setStatusRegistroAtivo(SimNaoEnum.SIM);
			usuario.setDataInclusao(Calendar.getInstance().getTime());
			super.incluir(usuario);

			enviarEmailConfirmacao(usuario, urlConfirmacao);

		} catch (Exception e) {
			throw new WeblyNegocioException(new Erro(
					MensagemErro.ERRO_INESPERADO.getCodigo()));
		}
	}

	@Override
	@Transactional
	public void alterar(final Usuario usuario) {

		if (StringUtils.isNotBlank(usuario.getNovaSenha())
				&& usuario.getNovaSenha()
						.equals(usuario.getConfirmaNovaSenha())) {
			usuario.setSenha(usuario.getNovaSenha());
			popularPasswordHash(usuario);
		}

		usuario.setDataAlteracao(Calendar.getInstance().getTime());
		super.alterar(usuario);
	}

	@Override
	@Transactional
	public void excluir(final Serializable id) {
		// FIXME: verificar se existe vínculo com outra tabela
		super.excluir(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void confirmarUsuario(final ConfirmaUsuarioVo confirmaUsuario) {

		validarUsuarioParaConfirmacao(confirmaUsuario);

	}

	private void validarUsuario(final Usuario usuario) {
		validarObrigatorio(usuario);
		validarDadoInvalido(usuario);
	}

	private void validarUsuarioParaAutenticacao(final LoginVo loginVo) {
		validarUsuarioParaAutenticacaoObrigatorios(loginVo);
		validarUsuarioParaAutenticacaoDadoInvalido(loginVo);
	}

	private void validarUsuarioParaConfirmacao(
			final ConfirmaUsuarioVo confirmaUsuario) {
		validarUsuarioParaConfirmacaoObrigatorios(confirmaUsuario);
		validarUsuarioParaConfirmacaoDadoInvalido(confirmaUsuario);
	}

	private void validarUsuarioParaAutenticacaoObrigatorios(
			final LoginVo loginVo) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (StringUtils.isBlank(loginVo.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL));
		}

		if (StringUtils.isBlank(loginVo.getPassword())) {

			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.SENHA));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}

	private void validarUsuarioParaConfirmacaoObrigatorios(
			final ConfirmaUsuarioVo confirmaUsuario) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (StringUtils.isBlank(confirmaUsuario.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL));
		}

		if (StringUtils.isBlank(confirmaUsuario.getCodigoVerificacao())) {

			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.CODIGO_VERIFICACAO));
		}

		if (StringUtils.isBlank(confirmaUsuario.getPassword())) {

			erros.add(new Erro(MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.SENHA));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}

	private void validarUsuarioParaAutenticacaoDadoInvalido(
			final LoginVo loginVo) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (UtilValidatorNomeUsuarioEmail
				.isTamanhoNomeUsuarioEmailInValido(loginVo
						.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO
					.getCodigo(), CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL));
		}

		if (UtilValidatorNomeUsuarioEmail.isNomeUsuarioEmailInValido(loginVo
				.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.NOME_USUARIO_OU_EMAIL_INVALIDO
					.getCodigo(), loginVo.getNomeUsuarioOuEmail()));
		}

		if (UtilValidadorPassword.isTamanhoInvalido(loginVo.getPassword())) {

			erros.add(new Erro(MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE
					.getCodigo(), CamposUsuarioEnum.SENHA,
					CamposUsuarioEnum.SENHA.getTamanhoMinimo(),
					CamposUsuarioEnum.SENHA.getTamanhoMaximo()));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}

	private void validarUsuarioParaConfirmacaoDadoInvalido(
			final ConfirmaUsuarioVo confirmaUsuario) {

		final List<Erro> erros = new ArrayList<Erro>();

		if (UtilValidatorNomeUsuarioEmail
				.isTamanhoNomeUsuarioEmailInValido(confirmaUsuario
						.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.TAMANHO_MAXIMO_CAMPO_INVALIDO
					.getCodigo(), CamposUsuarioEnum.NOME_USUARIO_OU_EMAIL));
		}

		if (UtilValidatorNomeUsuarioEmail
				.isNomeUsuarioEmailInValido(confirmaUsuario
						.getNomeUsuarioOuEmail())) {

			erros.add(new Erro(MensagemErro.NOME_USUARIO_OU_EMAIL_INVALIDO
					.getCodigo(), confirmaUsuario.getNomeUsuarioOuEmail()));
		}

		if (UtilValidadorCodigoVerificacao.isTamanhoInvalido(confirmaUsuario
				.getCodigoVerificacao())) {

			erros.add(new Erro(MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE
					.getCodigo(), CamposUsuarioEnum.CODIGO_VERIFICACAO));
		}

		if (UtilValidadorPassword.isTamanhoInvalido(confirmaUsuario
				.getPassword())) {
			erros.add(new Erro(MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE
					.getCodigo(), CamposUsuarioEnum.SENHA));
		}

		if (!erros.isEmpty()) {
			throw new WeblyNegocioException(erros);
		}
	}

	private void validarObrigatorio(final Usuario usuario) {

		if (StringUtils.isBlank(usuario.getSenha())) {
			throw new WeblyNegocioException(new Erro(
					MensagemErro.ERRO_CAMPO_OBRIGATORIO.getCodigo(),
					CamposUsuarioEnum.SENHA));
		}
	}

	private void validarDadoInvalido(final Usuario usuario) {

		if (UtilValidadorPassword.isTamanhoInvalido(usuario.getSenha())) {
			throw new WeblyNegocioException(new Erro(
					MensagemErro.TAMANHO_CAMPO_INVALIDO_RANGE.getCodigo(),
					CamposUsuarioEnum.SENHA,
					CamposUsuarioEnum.SENHA.getTamanhoMinimo(),
					CamposUsuarioEnum.SENHA.getTamanhoMaximo()));
		}
	}

	private void popularNomeUsuario(final Usuario usuario) {

		if (usuario.getPessoa() != null) {

			if (StringUtils.isNotBlank(usuario.getPessoa().getEmail())) {
				String[] partesEmail = usuario.getPessoa().getEmail()
						.split(ConstantesCore.SEPERADOR_EMAIL);

				if (partesEmail != null && partesEmail.length > 0) {
					usuario.setNome(partesEmail[0]);
				}
			}
		}
	}

	private void popularNomePublico(final Usuario usuario) {

		final String nomePessoa = usuario.getPessoa().getNome();

		int ix = nomePessoa.indexOf(" ");

		if (ix > 0) {
			usuario.setNomePublico(nomePessoa.substring(0, ix));
		} else {
			usuario.setNomePublico(nomePessoa);
		}
	}

	private void popularPasswordHash(final Usuario usuario) {
		usuario.setSenha(PasswordUtil.generateMD5(usuario.getSenha(),
				String.valueOf(ConstantesCore.charArrays)));
	}

	private void popularSituacaoUsuario(final Usuario usuario) {
		
		if (ParametrosCore.CONFIRMACAO_EMAIL_ATIVADA){

			usuario.setSituacaoUsuario(new SituacaoUsuario(
					SituacaoUsuarioEnum.AGUARDANDO_CONFIRMACAO_INCLUSAO
					.getCodigo()));
		}else{

			usuario.setSituacaoUsuario(new SituacaoUsuario(
					SituacaoUsuarioEnum.CONFIRMADO
					.getCodigo()));
		}
	}

	private void popularCodigoVerificacao(final Usuario usuario) {
		usuario.setCodigoVerificacao(UUIDChars.uuid(8));
	}

	private void enviarEmailConfirmacao(final Usuario usuario,
			final String urlConfirmacao) throws UnsupportedEncodingException,
			MessagingException {

		if (ParametrosCore.CONFIRMACAO_EMAIL_ATIVADA){

			final MensagemVo mensagem = new MensagemVo();
			
			mensagem.setAssunto("Ação Necessária para Ativar a Conta");
			
			popularMensagem(usuario, mensagem, urlConfirmacao);
			
			mensagem.setNomeDestinatario(usuario.getPessoa().getNome());
			mensagem.setEmailDestinatario(usuario.getPessoa().getEmail());
			
			emailService.sendEmailDoSistemaPara(mensagem);
		}
	}

	private void popularMensagem(final Usuario usuario,
			final MensagemVo mensagem, final String urlConfirmacao) {

		final StringBuilder msg = new StringBuilder();
		msg.append("Prezado(a) ");
		msg.append(usuario.getNome());
		msg.append(",");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Obrigado por registrar-se no ");
		msg.append(ParametrosCore.TITULO);
		msg.append(".");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("Antes de ativar sua conta é necessário um passo adicional para completar seu registro.");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Por favor, observe que você deve completar esta etapa para tornar-se um usuário registrado e que apenas é necessário visitar");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("esse endereço uma única vez para ativar sua conta.");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("Para completar seu registro, por favor visite o seguinte endereço:");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		popularLinkParametros(usuario, msg, urlConfirmacao);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("**** O Endereço informado acima não funcionou? ****");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("Então use seu navegador e vá para:");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		popularLink(msg, urlConfirmacao);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Certifique-se de não adicionar espaços extras. Você terá de digitar seu nome de usuário e o código de ativação na página que");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("aparecerá ao visitar esse endereço.");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Seu Nome de Usuário é: ");
		msg.append(usuario.getNome());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("Seu Código de Ativação é: ");
		msg.append(usuario.getCodigoVerificacao());
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Se ainda assim você tiver problemas de registro, por favor entre em contato pelo seguinte endereço ");
		msg.append(emailDestinatarioEndereco);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("Atenciosamente,");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ParametrosCore.TITULO);
		msg.append(".");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);

		msg.append("ATENÇÃO:");
		msg.append(ConstantesCore.QUEBRA_LINHA_HTML);
		msg.append("Não responda a esta mensagem. Ela é apenas uma notificação automática do sistema.");

		mensagem.setTextoMensagem(msg.toString());
	}

	private void popularLinkParametros(final Usuario usuario,
			final StringBuilder msg, final String urlConfirmacao) {

		String url = substituirToken(urlConfirmacao,
				ConstantesCore.PARAMETRO_GET_USUARIO, usuario.getNome());

		msg.append(substituirToken(url,
				ConstantesCore.PARAMETRO_GET_CODIGO_VERIFICACAO,
				usuario.getCodigoVerificacao()));
	}

	private void popularLink(final StringBuilder msg,
			final String urlConfirmacao) {

		String[] partes = urlConfirmacao.split(ConstantesCore.EXTENSAO_HTML);

		if (partes.length == 2) {
			msg.append(partes[0]);
			msg.append(ConstantesCore.EXTENSAO_HTML);
		}
	}

	private String substituirToken(final String url, final String parametro,
			final String valorParametro) {

		final StringBuilder sbBuscar = new StringBuilder(parametro);
		sbBuscar.append(ConstantesCore.SEPARADOR_PARAM_HTML);
		sbBuscar.append(ConstantesCore.TOKEN);

		final StringBuilder sbSubstituir = new StringBuilder(parametro);
		sbSubstituir.append(ConstantesCore.SEPARADOR_PARAM_HTML);
		sbSubstituir.append(valorParametro);

		return url.replaceAll(sbBuscar.toString(), sbSubstituir.toString());
	}

	private Usuario criarUsuario(final LoginVo loginVo) {

		final Usuario usuario = new Usuario();
		usuario.setPessoa(new Pessoa());
		usuario.setSenha(loginVo.getPassword());

		if (loginVo.getNomeUsuarioOuEmail().contains(
				ConstantesCore.SEPERADOR_EMAIL)) {
			usuario.getPessoa().setEmail(loginVo.getNomeUsuarioOuEmail());
		} else {
			usuario.setNome(loginVo.getNomeUsuarioOuEmail());
		}
		return usuario;
	}
}