package org.rulez.demokracia.zenta3.editor.tree;

import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class ZentaCellFactory
    implements Callback<TreeView<ZentaElement>, TreeCell<ZentaElement>> {

  @Override
  public TreeCell<ZentaElement> call(final TreeView<ZentaElement> view) {
    final ZentaTreeCell cell = new ZentaTreeCell(view);

    return cell;
  }

}
