package org.rulez.demokracia.zenta3.editor.tree;

import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.scene.control.TreeView;

public class ZentaTreeView extends TreeView<ZentaElement> {

  public ZentaTreeView() {
    super();
    setCellFactory(new ZentaCellFactory());
  }
}
