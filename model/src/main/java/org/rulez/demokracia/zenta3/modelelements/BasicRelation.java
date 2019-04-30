package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.demokracia.zenta3.model.ZentaRelation;
import org.w3c.dom.Element;

public class BasicRelation extends ZentaElementImpl implements ZentaRelation {

	public BasicRelation(final Folder folder) {
		super(folder);
		setNodeBasicInfo(ZentaElement.BASIC_RELATION, ZentaElement.BASIC_RELATION_NAME,
				ZentaElement.BASIC_RELATION_DOCUMENTATION);
		setNodeAttribute("source", ZentaElement.THING);
		setNodeAttribute("target", ZentaElement.THING);
		setNodeAttribute("ancestor", "thing");
	}

	public BasicRelation(final ZentaModel model, final Element elementNode) {
		super(model, elementNode);
	}

}
