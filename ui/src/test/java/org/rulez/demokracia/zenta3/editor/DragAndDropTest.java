package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeCell;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import javafx.scene.control.TreeCell;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class DragAndDropTest extends ApplicationTester {

  private ZentaElement dragBoardContent;
  private ZentaElement newElement;

  @Override
  @Start
  public void start(final Stage stage) throws Exception {
    super.start(stage);
  }

  @BeforeEach
  public void setUp(final FxRobot robot) {
    expandModel(robot);
    newElement = model.createElement(rootFolder, thing);
    newElement.setName("new element");
    WaitForAsyncUtils.waitForFxEvents();

  }

  @Test
  public void
      when_an_element_is_added_it_is_seen_in_the_tree(final FxRobot robot) throws InterruptedException {

    final TreeCell<ZentaElement> cell = findCellFor(newElement);

    assertEquals(newElement, cell.getTreeItem().getValue());
  }

  @Test
  public void
      when_the_drag_is_over_the_style_of_the_cell_have_dragtarget_style(
          final FxRobot robot
      ) {
    final ZentaTreeCell thingCell = findCellFor(thing);
    robot.drag(getModelRootCell()).moveTo(thingCell);
    assertTrue(thingCell.getStyleClass().contains("dragtarget"));
    robot.drop();
  }

  @Test
  public void
      when_the_drag_is_dropped_the_element_is_moved(
          final FxRobot robot
      ) throws InterruptedException {
    final ZentaTreeCell startCell = findCellFor(newElement);
    System.out.println("start cell at " + startCell.getIndex());
    robot.drag(startCell);
    final ZentaTreeCell targetCell = findCellFor(thing);
    final int targetIndex = targetCell.getIndex();
    System.out.println("target cell at " + targetIndex);
    robot.moveTo(targetCell);
    robot.dropTo(targetCell);
    WaitForAsyncUtils.waitForFxEvents();

    final ZentaTreeCell endCell = findCellFor(newElement);
    System.out.println("end cell at " + endCell.getIndex());
    assertEquals(targetIndex + 1, endCell.getIndex());
  }
}
