package org.rulez.demokracia.zenta3.editor.widgets;

import org.eclipse.gef.fx.nodes.InfiniteCanvas;
import org.rulez.demokracia.zenta3.editor.parts.ZentaElementPart;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeCell;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.event.ActionEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Rectangle;

public class ZentaCanvas extends InfiniteCanvas {

  private final Folder folder;

  public ZentaCanvas(final Folder folder) {
    this.folder = folder;
    setOnDragDropped((final DragEvent event) -> drop(event, this));
    setOnDragDone((final DragEvent event) -> drop(event, this));
  }

  public void addElement(final ActionEvent event, final ZentaElement element) {
    getContentGroup().getChildren()
        .add(new ZentaElementPart(element));
  }

  private void drop(
      final DragEvent event, final ZentaCanvas canvas
  ) {
    final Dragboard db = event.getDragboard();
    final boolean success = false;
    if (!db.hasContent(ZentaTreeCell.ZENTA_FORMAT))
      return;

    final ZentaElement content =
        (ZentaElement) db.getContent(ZentaTreeCell.ZENTA_FORMAT);
    getChildren().add(new Rectangle());
    event.setDropCompleted(success);
  }
}
