package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeCell;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeItem;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeView;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TreeModelViewTest extends ApplicationTester {

  @Override
  @Start
  public void start(final Stage stage) throws Exception {
    super.start(stage);
  }

  @Test
  public void test_the_main_pane_have_a_tree_model_view_at_left() {
    final BorderPane pane = mainPane;
    final Node treeModel = pane.getLeft();
    assertEquals(ZentaTreeView.class, treeModel.getClass());
  }

  @Test
  public void the_tree_model_contains_models() {
    final TreeItem<ZentaElement> elem = root.getChildren().get(0);
    assertTrue(
        ApplicationTester.JUST_CREATED_MODEL.equals(elem.getValue().getName())
    );
  }

  @Test
  public void the_model_element_is_in_our_TreeItem() {
    final TreeItem<ZentaElement> elem = root.getChildren().get(0);
    assertEquals(ZentaTreeItem.class, elem.getClass());
  }

  @Test
  public void the_tree_model_root_label_is_Models() {
    assertEquals("Models", root.getValue().toString());
  }

  @Test
  public void
      model_can_be_expanded(final FxRobot robot) throws InterruptedException {
    final Folder rootFolder = currentModel.getModel().getRootFolder();
    final ZentaTreeCell cell = findCellFor(rootFolder);
    final ZentaElement stuff = rootFolder.getElements().get(0);
    assertNull(findCellFor(stuff));
    robot.doubleClickOn(cell);
    assertNotNull(findCellFor(stuff));
  }

}
