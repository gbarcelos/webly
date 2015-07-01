package br.com.oak.webly.pages.colaborador.post;

import java.util.Iterator;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.oak.core.entidade.Paginacao;
import br.com.oak.webly.core.service.PostService;
import br.com.oak.webly.core.util.ParametrosCore;
import br.com.oak.webly.core.vo.PostVo;
import br.com.oak.webly.core.vo.filtro.FiltroPostVo;
import br.com.oak.wicket.ui.grid.provider.DataProvider;

public class PostProvider extends DataProvider<PostVo> {

	private static final long serialVersionUID = 5133173722732449302L;

	private boolean jaBuscou;

	private int quantidade;

	private PostService service;

	private FiltroPostVo filtro;

	public PostProvider(final PostService service) {
		this.service = service;
		setItensPorPagina(ParametrosCore.NUMERO_ITENS_POR_PAGINA);
	}

	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends PostVo> iterator(int first, int count) {

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
	public IModel<PostVo> model(final PostVo object) {
		return new LoadableDetachableModel<PostVo>() {

			private static final long serialVersionUID = 3542098889497399064L;

			@Override
			protected PostVo load() {
				return object;
			}
		};
	}

	private FiltroPostVo getFiltro() {
		if (filtro == null) {
			filtro = new FiltroPostVo();
		}
		return filtro;
	}

	public void setFiltro(final FiltroPostVo filtro) {
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