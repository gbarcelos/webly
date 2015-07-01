package br.com.oak.webly.pages.componentes.autor;

import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

import br.com.oak.webly.core.vo.UsuarioVo;

public abstract class UsuarioConverter implements IConverter<UsuarioVo> {

	private static final long serialVersionUID = 1715092036141714452L;

	@Override
	public UsuarioVo convertToObject(final String value, final Locale locale) {
		return getUsuarioSelecionado();
	}

	@Override
	public String convertToString(final UsuarioVo value, final Locale locale) {
		setUsuarioSelecionado(value);
		return value.getNome();
	}

	public abstract UsuarioVo getUsuarioSelecionado();

	public abstract void setUsuarioSelecionado(final UsuarioVo usuario);
}