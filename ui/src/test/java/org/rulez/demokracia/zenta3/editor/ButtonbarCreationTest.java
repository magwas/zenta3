package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ButtonbarCreationTest extends ApplicationTester {

  @Override
  @Start
  public void start(final Stage stage) throws Exception {
    super.start(stage);
  }

  @Test
  public void test_the_main_pane_have_a_button_bar() {
    final Node button = buttonBar.getChildrenUnmodifiable().get(0);
    assertEquals(Button.class, button.getClass());
  }

  @Test
  public void the_button_bar_have_a_Undo_Button() {
    assertLabelInParent("Undo", buttonBar);
  }

  @Test
  public void the_button_bar_have_a_Redo_Button() {
    assertLabelInParent("Redo", buttonBar);
  }

  @Test
  public void the_button_bar_have_a_Save_Button() {
    assertLabelInParent("Save", buttonBar);
  }

}
