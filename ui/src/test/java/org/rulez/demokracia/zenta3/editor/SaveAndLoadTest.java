package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.rulez.demokracia.zenta3.editor.model.EditorModelFactory;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.control.Button;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class SaveAndLoadTest extends ApplicationTester {

  @Override
  @Start
  public void start(final Stage stage) throws Exception {
    super.start(stage);
  }

  @Test
  public void test_save_saves_if_a_path_is_already_set() throws IOException {
    currentModel.setPath(file);
    currentModel.save();
    final ZentaModel newModel = ZentaModel.loadFile(file);
    assertEquals(
        currentModel.getModel().getRootFolder().getId(),
        newModel.getRootFolder().getId()
    );
  }

  @Test
  public void save_does_nothing_when_no_file_specified() throws IOException {
    final Button saveButton = (Button) findLabelInParent("Save", buttonBar);
    Mockito
        .when(
            EditorModelFactory.widgetFactory
                .chooseFileToSave(Mockito.anyString())
        )
        .thenReturn(null);
    saveButton.fire();
    assertFalse(file.toFile().exists());
  }

  @Test
  public void save_asks_for_path_if_no_file_specified() throws IOException {
    final Button saveButton = (Button) findLabelInParent("Save", buttonBar);
    Mockito
        .when(
            EditorModelFactory.widgetFactory
                .chooseFileToSave(Mockito.anyString())
        )
        .thenReturn(file.toFile());
    saveButton.fire();
    assertTrue(file.toFile().exists());
  }

  @Test
  public void save_sets_model_path_in_first_save() throws IOException {
    final Button saveButton = (Button) findLabelInParent("Save", buttonBar);
    Mockito
        .when(
            EditorModelFactory.widgetFactory
                .chooseFileToSave(Mockito.anyString())
        )
        .thenReturn(file.toFile());
    saveButton.fire();
    Files.deleteIfExists(file);
    saveButton.fire();
    assertTrue(file.toFile().exists());
    Mockito.verify(EditorModelFactory.widgetFactory, Mockito.times(1))
        .chooseFileToSave(Mockito.anyString());
  }

  @Test
  public void load_asks_for_a_file(final FxRobot robot) {
    robot.interact(() -> loadFile());
    Mockito.verify(EditorModelFactory.widgetFactory, Mockito.times(1))
        .chooseFileToLoad(Mockito.anyString());
  }

  @Test
  public void load_loads_the_file(final FxRobot robot) {
    robot.interact(() -> loadFile());
    loadFile();
    assertLoadedOnce();
  }

  @Test
  public void second_load_does_not_load_again(final FxRobot robot) {
    robot.interact(() -> loadFile());
    robot.interact(() -> loadFile());
    assertLoadedOnce();
  }
}
