package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.w3c.dom.Element;

public final class Thing extends ZentaElementImpl {

	public Thing(final Folder folder) {
		super(folder);
		setNodeBasicInfo(ZentaElement.THING, ZentaElement.THING_NAME, ZentaElement.THING_DOCUMENTATION);
		setNodeAttribute("ancestor", "thing");
	}

	public Thing(final ZentaModel model, final Element elementNode) {
		super(model, elementNode);
	}

}
