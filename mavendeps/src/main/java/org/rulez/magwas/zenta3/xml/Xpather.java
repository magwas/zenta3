package org.rulez.magwas.zenta3.xml;

import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.rulez.magwas.errors.ReportedError;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Xpather {

	private final Node node;

	public Xpather(final Node node) {
		this.node = node;
	}

	public Object evaluateXpathWithVariables(final String xpathExpression, final QName returnType,
			final Map<String, Object> vars) {
		MapVariableResolver variableResolver = new MapVariableResolver();
		Iterator<Map.Entry<String, Object>> iterator = vars.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			variableResolver.setVariable(entry.getKey(), entry.getValue());
		}
		XPathFactory xPathfactory = new net.sf.saxon.xpath.XPathFactoryImpl();
		XPath xpath = xPathfactory.newXPath();
		NamespaceContext nsContext = new MyNamespaceContext();
		xpath.setNamespaceContext(nsContext);
		xpath.setXPathVariableResolver(variableResolver);
		Object result;
		try {
			XPathExpression expr = xpath.compile(xpathExpression);
			result = expr.evaluate(node, returnType);
		} catch (XPathExpressionException e) {
			throw new ReportedError("problem evaluating expression:",
					String.format("%s:\n\t%s", xpathExpression, e.getMessage()));
		}
		return result;
}

	public Element getElementById(final String targetId) {
		return (Element) evaluateXpathWithVariables(
				"//*[@id=$id]",
				XPathConstants.NODE,
				Map.of("id", targetId));
	}

}