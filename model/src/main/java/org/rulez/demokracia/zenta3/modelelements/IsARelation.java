package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.w3c.dom.Element;

public class IsARelation extends BasicRelation {

	public IsARelation(final Folder folder) {
		super(folder);
		setNodeBasicInfo(ZentaElement.IS_A_RELATION, ZentaElement.IS_A_RELATION_NAME,
				ZentaElement.IS_A_RELATION_DOCUMENTATION);
		this.setNodeAttribute(ZentaElement.ANCESTOR_ATTRIBUTE, ZentaElement.BASIC_RELATION);
	}

	public IsARelation(final ZentaModel model, final Element elementNode) {
		super(model, elementNode);
	}
}
