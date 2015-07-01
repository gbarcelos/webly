package br.com.oak.webly.application;

import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.resource.loader.IStringResourceLoader;

import br.com.oak.webly.core.util.ResourceUtil;

public class PropertiesResourceLoader implements IStringResourceLoader {

	@Override
	public String loadStringResource(Class<?> clazz, String key, Locale locale,
			String style, String variation) {

		return loadString(key, locale, style, variation);
	}

	@Override
	public String loadStringResource(Component component, String key,
			Locale locale, String style, String variation) {

		if (component != null) {
			return loadString(component, key, locale, style, variation);
		} else {
			return loadString(key, locale, style, variation);
		}
	}

	private String loadString(String key, Locale locale, String style,
			String variation) {

		return ResourceUtil.recuperaLabel(key);
	}

	private String loadString(Component component, String key, Locale locale,
			String style, String variation) {

		String stringRecurso = key;

		if (StringUtils.isNotBlank(key)) {

			stringRecurso = ResourceUtil.recuperaLabel(key.substring(key.indexOf(".") + 1));
		}
		return stringRecurso;
	}
}