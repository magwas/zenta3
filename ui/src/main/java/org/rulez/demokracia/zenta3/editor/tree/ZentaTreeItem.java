package org.rulez.demokracia.zenta3.editor.tree;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class ZentaTreeItem extends TreeItem<ZentaElement> {

  private boolean gotChildren = false;

  public ZentaTreeItem(final ZentaElement element) {
    setValue(element);
  }

  @Override
  public boolean isLeaf() {
    return !(getValue() instanceof Folder);
  }

  @Override
  public ObservableList<TreeItem<ZentaElement>> getChildren() {
    if (isLeaf() || gotChildren)
      return super.getChildren();
    final ObservableList<TreeItem<ZentaElement>> ol = super.getChildren();
    final Folder folder = (Folder) getValue();
    for (final ZentaElement e : folder.getElements())
      ol.add(new ZentaTreeItem(e));
    gotChildren = true;
    return ol;
  }
}
