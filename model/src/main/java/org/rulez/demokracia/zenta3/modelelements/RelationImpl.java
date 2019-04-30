package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.demokracia.zenta3.model.ZentaRelation;
import org.w3c.dom.Element;

public class RelationImpl extends ZentaElementImpl implements ZentaRelation {

	public RelationImpl(final Folder folder, final ZentaElement parent, final ZentaElement sourceElement,
			final ZentaElement targetElement) {
		super(folder, parent);
		setSource(sourceElement);
		setTarget(targetElement);
	}

	public RelationImpl(final ZentaModel model, final Element elementNode) {
		super(model, elementNode);
	}
}
