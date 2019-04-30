package org.rulez.magwas.zenta3.xml;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;

public class MapVariableResolver implements XPathVariableResolver {
	final private Map<String, Object> variableMappings = new ConcurrentHashMap<>();

	public void setVariable(final String name, final Object value) {
		variableMappings.put(name, value);
	}

	@Override
	public Object resolveVariable(final QName varName) {
		String key = varName.getLocalPart();
		return variableMappings.get(key);
	}
}
