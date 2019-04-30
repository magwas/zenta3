package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.w3c.dom.Element;

public class MetaFolder extends FolderImpl {

	public MetaFolder(final Folder parentFolder) {
		super(parentFolder);
		setNodeBasicInfo(ZentaElement.FOLDER, ZentaElement.FOLDER_NAME, ZentaElement.FOLDER_DOCUMENTATION);
		this.setNodeAttribute(ZentaElement.ANCESTOR_ATTRIBUTE, ZentaElement.THING);

	}

	public MetaFolder(final ZentaModel model, final Element elementNode) {
		super(model, elementNode);
	}

}
