package org.rulez.demokracia.zenta3;

import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.w3c.dom.Document;

public interface ZentaModelInterface {

	Document getXml();

	ZentaElement catalogize(ZentaElement zentaElement);

}