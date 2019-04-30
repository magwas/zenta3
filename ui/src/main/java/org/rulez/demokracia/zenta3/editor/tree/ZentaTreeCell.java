package org.rulez.demokracia.zenta3.editor.tree;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Map;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class ZentaTreeCell extends TreeCell<ZentaElement> {

  public static final DataFormat ZENTA_FORMAT =
      new DataFormat("Zenta Elements");
  private final TreeView<ZentaElement> treeView;

  public ZentaTreeCell(final TreeView<ZentaElement> view) {
    super();
    treeView = view;
    setOnDragDetected((e) -> dragDetected(e));
    setOnDragEntered((e) -> dragEntered(e));
    setOnDragOver((e) -> dragOver(e));
    setOnDragExited((e) -> dragExited(e));
    setOnDragDropped((e) -> dragDropped(e));
    setOnDragDone((e) -> dragDone(e));
  }

  private void dragDone(final DragEvent e) {
    System.out.println("done\n\tat " + this + "\n\t event: " + e);
  }

  private void dragDropped(final DragEvent e) {
    System.out.println("dropped\n\tat " + this + "\n\t event: " + e);
    e.setDropCompleted(true);
    e.getDragboard().clear();
    e.consume();
    getStyleClass().remove("dragtarget");
    final ZentaTreeCell gestureSource = (ZentaTreeCell) e.getGestureSource();
    final ZentaTreeCell gestureTarget = (ZentaTreeCell) e.getGestureTarget();

    final TreeItem<ZentaElement> draggedItem = gestureSource.getTreeItem();
    final TreeItem<ZentaElement> draggedItemParent = draggedItem.getParent();
    final TreeItem<ZentaElement> droppeditem = gestureTarget.getTreeItem();
    final TreeItem<ZentaElement> droppedItemParent = droppeditem.getParent();
    final int indexInParent =
        droppedItemParent.getChildren().indexOf(droppeditem);
    draggedItemParent.getChildren().remove(draggedItem);
    droppedItemParent.getChildren().add(indexInParent + 1, draggedItem);
    treeView.getSelectionModel().select(draggedItem);
  }

  private void dragOver(final DragEvent e) {
    System.out.println("over\n\tat " + this + "\n\t event: " + e);
    e.acceptTransferModes(TransferMode.MOVE);
    e.consume();
  }

  private void dragEntered(final DragEvent e) {
    System.out.println("entered\n\tat " + this + "\n\t event: " + e);
    getStyleClass().add("dragtarget");
    e.consume();
  }

  private void dragExited(final DragEvent e) {
    System.out.println("exited\n\tat " + this + "\n\t event: " + e);
    getStyleClass().remove("dragtarget");
    e.consume();
  }

  public void dragDetected(final MouseEvent e) {
    System.out.println("detected\n\tat " + this + "\n\t event: " + e);
    System.out.println("dragDetected");
    final Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
    final Map<DataFormat, Object> map = new HashMap<>();
    final ZentaElement item = getItem();
    map.put(ZENTA_FORMAT, item);
    System.out.println("item: " + item);
    dragBoard.setContent(map);

    e.consume();
  }

  @Override
  public void updateItem(final ZentaElement item, final boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setText(null);
      setGraphic(null);
      final ZentaElement currentItem = getItem();
      if (null != currentItem)
        currentItem.removePropertyChangeListener((e) -> propertyChanged(e));
      setItem(null);
    } else {
      setText(item.getName());
      item.addPropertyChangeListener((e) -> propertyChanged(e));
    }
    setItem(item);
  }

  private void propertyChanged(final PropertyChangeEvent e) {
    if (Folder.CHILDREN_PROPERTY.contentEquals(e.getPropertyName())) {
      final TreeItem<ZentaElement> item =
          new ZentaTreeItem((ZentaElement) e.getNewValue());
      final TreeItem<ZentaElement> folderItem = getTreeItem();
      if (!(folderItem instanceof ZentaTreeItem))
        return;
      final ZentaTreeItem folderCell = (ZentaTreeItem) folderItem;
      folderCell.getChildren().add(item);
    }
  }

}
