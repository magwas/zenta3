package org.rulez.demokracia.zenta3.editor.widgets;

import java.io.File;

import javax.inject.Singleton;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

@Singleton
public class InteractiveWidgets implements IInteractiveWidgets {

  private static Stage stage;

  InteractiveWidgets() {

  }

  public static void setStage(final Stage theStage) {
    stage = theStage;
  }

  @Override
  public File chooseFileToSave(final String title) {
    final FileChooser fileChooser = createFileChooser(title);
    final File file = fileChooser.showSaveDialog(stage);
    return file;
  }

  private FileChooser createFileChooser(final String title) {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    return fileChooser;
  }

  @Override
  public File chooseFileToLoad(final String title) {
    final FileChooser fileChooser = createFileChooser(title);
    final File file = fileChooser.showOpenDialog(stage);
    return file;
  }

}
