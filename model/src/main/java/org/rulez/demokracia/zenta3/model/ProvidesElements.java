package org.rulez.demokracia.zenta3.model;

import java.beans.PropertyChangeEvent;

import org.rulez.demokracia.zenta3.ZentaModelInterface;
import org.rulez.demokracia.zenta3.modelelements.FolderImpl;
import org.rulez.demokracia.zenta3.modelelements.RelationImpl;
import org.rulez.demokracia.zenta3.modelelements.ZentaElementImpl;
import org.rulez.magwas.errors.ReportedError;

public interface ProvidesElements extends ZentaModelInterface {

  ZentaElement getElementById(String elementId);

  default ZentaElement
      createElement(final Folder folder, final ZentaElement parent) {
    ZentaElement zentaElement;
    if (parent instanceof ZentaRelation)
      throw new ReportedError(
          "use createRelationship to create a relationship"
      );
    else {
      zentaElement = new ZentaElementImpl(folder, parent);
      final PropertyChangeEvent event = new PropertyChangeEvent(
          zentaElement, Folder.CHILDREN_PROPERTY, null, zentaElement
      );
      folder.firePropertyChange(event);
    }
    return catalogize(zentaElement);
  }

  default ZentaRelation createRelation(
      final Folder folder, final ZentaElement parent,
      final ZentaElement sourceElement, final ZentaElement destinationElement
  ) {
    final ZentaRelation relation =
        new RelationImpl(folder, parent, sourceElement, destinationElement);
    catalogize(relation);
    return relation;
  }

  default Folder getRootFolder() {
    final Folder folder = new FolderImpl((ZentaModel) this);
    return (Folder) catalogize(folder);
  }

  default Folder createFolder(final Folder rootFolder) {
    final Folder folder = new FolderImpl(rootFolder);
    return (Folder) catalogize(folder);
  }
}
