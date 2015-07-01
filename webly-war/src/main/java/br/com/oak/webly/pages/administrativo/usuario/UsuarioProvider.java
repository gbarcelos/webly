package br.com.oak.webly.pages.administrativo.usuario;

import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.oak.core.entidade.Paginacao;
import br.com.oak.core.entidade.ParametroDeOrdenacao;
import br.com.oak.webly.core.service.UsuarioService;
import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.vo.UsuarioVo;
import br.com.oak.webly.core.vo.filtro.FiltroUsuarioVo;
import br.com.oak.wicket.ui.grid.Ordenacao;
import br.com.oak.wicket.ui.grid.provider.DataProvider;

public class UsuarioProvider extends DataProvider<UsuarioVo> {

	private static final long serialVersionUID = 5415452674190397159L;

	private boolean jaBuscou;

	private Ordenacao ordenacao;

	private int quantidade;

	private UsuarioService service;

	private FiltroUsuarioVo filtro;

	public UsuarioProvider(UsuarioService service) {
		this.service = service;
		setItensPorPagina(ParametrosCore.NUMERO_ITENS_POR_PAGINA);
	}

	@Override
	public Iterator<? extends UsuarioVo> iterator(int first, int count) {

		getFiltro().setParametroDeOrdenacao(
				new ParametroDeOrdenacao(getOrdenacao().getCampo(),
						getOrdenacao().getOrdenacao()));

		return service.recuperaRegistros(getFiltro(),
				new Paginacao(first, count)).iterator();
	}

	@Override
	public int size() {
		if (!isJaBuscou()) {
			quantidade = service.recuperaQuantidadeRegistros(getFiltro())
					.intValue();
			setJaBuscou(true);
		}
		return quantidade;
	}

	@Override
	public IModel<UsuarioVo> model(final UsuarioVo usuarioVo) {
		return new LoadableDetachableModel<UsuarioVo>() {

			private static final long serialVersionUID = 6169033804540896945L;

			@Override
			protected UsuarioVo load() {
				return usuarioVo;
			}
		};
	}

	@Override
	public void detach() {
	}

	public Ordenacao getOrdenacao() {
		if (ordenacao == null) {
			ordenacao = new Ordenacao("nomePessoa", "ASC");
		}
		return ordenacao;
	}

	private FiltroUsuarioVo getFiltro() {
		if (filtro == null) {
			filtro = new FiltroUsuarioVo();
		}
		return filtro;
	}

	public void setFiltro(final FiltroUsuarioVo filtro) {
		this.filtro = filtro;
		setJaBuscou(false);
	}

	private boolean isJaBuscou() {
		return jaBuscou;
	}

	private void setJaBuscou(boolean jaBuscou) {
		this.jaBuscou = jaBuscou;
	}
}