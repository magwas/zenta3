package org.rulez.demokracia.zenta3.editor.tree;

import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.scene.control.TreeItem;

public class TreeRoot extends TreeItem<ZentaElement> {

  public TreeRoot() {
    setExpanded(true);
    setValue(new TreeRootElement());
  }
}
