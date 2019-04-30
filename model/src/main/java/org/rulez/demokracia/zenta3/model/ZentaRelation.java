package org.rulez.demokracia.zenta3.model;

import org.w3c.dom.Element;

public interface ZentaRelation extends ZentaElement {

	default ZentaElement getSource() {
		return getElementFromAttr("source");
	}

	default void setSource(ZentaElement sourceElement) {
		setAttrAsElement("source", sourceElement);
	}

	default ZentaElement getTarget() {
		return getElementFromAttr("target");
	}

	default void setTarget(ZentaElement targetElement) {
		setAttrAsElement("target", targetElement);
	}

	private Element getXmlElement() {
		Element element = (Element) adapt(Element.class);
		return element;
	}

	private ZentaElement getElementFromAttr(String attName) {
		String idValue = getXmlElement().getAttribute(attName);
		return getModel().getElementById(idValue);
	}

	private void setAttrAsElement(String attName, ZentaElement sourceElement) {
		Element element = getXmlElement();
		element.setAttribute(attName, sourceElement.getId());
	}

}
