package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rulez.demokracia.zenta3.editor.widgets.ZentaPalette;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class TabPaneTest extends ApplicationTester {

  @Override
  @Start
  public void start(final Stage stage) throws Exception {
    super.start(stage);
  }

  @Test
  public void test_the_center_of_the_main_pane_is_a_tab_pane() {
    final Class<? extends Node> centerClass = tabPane.getClass();
    assertEquals(TabPane.class, centerClass);
  }

  @Test
  public void
      if_a_model_is_loaded_a_new_tab_appears_for_it(final FxRobot robot) {
    final int origSize = tabPane.getTabs().size();
    robot.interact(() -> loadFile());
    assertTrue(origSize + 1 == tabPane.getTabs().size());
  }

  @Test
  public void the_tab_label_contains_the_model_name() {
    final ObservableList<Tab> tabs = tabPane.getTabs();
    final String label = tabs.get(0).getText();
    assertEquals(JUST_CREATED_MODEL, label);
  }

  @Test
  public void there_is_a_toolbar_for_the_current_model() {
    final Node palette = mainPane.getRight();
    assertEquals(ZentaPalette.class, palette.getClass());
  }

}
