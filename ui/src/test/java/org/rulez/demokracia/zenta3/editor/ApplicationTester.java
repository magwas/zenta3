package org.rulez.demokracia.zenta3.editor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.rulez.demokracia.zenta3.editor.model.EditorModelFactory;
import org.rulez.demokracia.zenta3.editor.model.EditorModelItem;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeCell;
import org.rulez.demokracia.zenta3.editor.widgets.InteractiveWidgets;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.testfx.api.FxRobot;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationTester {

  public static final String JUST_CREATED_MODEL = "just created model";
  protected Parent buttonBar;
  protected EditorModelItem currentModel;
  protected BorderPane mainPane;
  protected Path file;
  protected TreeItem<ZentaElement> root;
  protected TreeView<ZentaElement> treeView;
  protected TabPane tabPane;
  protected Folder rootFolder;
  protected ZentaModel model;
  protected ZentaElement thing;

  public void start(final Stage stage) throws Exception {
    final ZentaApplication app = new ZentaApplication();
    app.start(stage);
    treeView = ZentaApplication.getTreeView();
    mainPane = ZentaApplication.mainPane;
    buttonBar = (Parent) mainPane.getTop();
    tabPane = (TabPane) mainPane.getCenter();
    root = treeView.getRoot();

    MockitoAnnotations.initMocks(this);
    EditorModelFactory.widgetFactory = Mockito.mock(InteractiveWidgets.class);
    file = Paths.get("/tmp/model.zenta3");
    try {
      Files.deleteIfExists(file);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
    EditorModelFactory.getModels().clear();
    model = ZentaModel.create();
    model.getRootFolder().setName(JUST_CREATED_MODEL);
    EditorModelFactory.loadModel(model);
    currentModel = EditorModelFactory.currentModel;
    rootFolder = model.getRootFolder();
    thing = model.getElementById(ZentaElement.THING);

  }

  protected Labeled
      findLabelInParent(final String labelToFind, final Parent parent) {
    for (final Node button : parent.getChildrenUnmodifiable())
      if (button instanceof Labeled) {
        final Labeled labeled = (Labeled) button;
        if (labelToFind.equals(labeled.getText()))
          return labeled;
      }
    return null;
  }

  protected void
      assertLabelInParent(final String labelToFind, final Parent parent) {
    if (null == findLabelInParent(labelToFind, parent))
      fail("label not found: " + labelToFind);
  }

  protected Button loadFile() {
    final Button loadButton = (Button) findLabelInParent("Load", buttonBar);
    final String modelPath = Thread.currentThread().getContextClassLoader()
        .getResource("model.zenta3").getPath();
    Mockito
        .when(
            EditorModelFactory.widgetFactory
                .chooseFileToLoad(Mockito.anyString())
        )
        .thenReturn(new File(modelPath));
    loadButton.fire();
    return loadButton;
  }

  protected void assertLoadedOnce() {
    int found = 0;

    for (final EditorModelItem model : EditorModelFactory.getModels())
      if (model.getModel().getRootFolder().getId().equals("TestModel"))
        found++;
    assertEquals(1, found);
  }

  protected void
      printTree(final TreeItem<ZentaElement> treeView, final int depth) {
    System.out.printf("%s%s\n", StringUtils.repeat(" ", depth), treeView);
    for (final TreeItem<ZentaElement> e : treeView.getChildren())
      printTree(e, depth + 1);
  }

  protected ZentaTreeCell findCellFor(final ZentaElement element) {
    final VirtualFlow<?> flow =
        (VirtualFlow<?>) treeView.getChildrenUnmodifiable().get(0);
    for (int i = 0; i < flow.getCellCount(); i++) {
      final ZentaTreeCell cell = (ZentaTreeCell) flow.getCell(i);
      final ZentaElement item = cell.getItem();
      if (element.getId().equals(item.getId()))
        return cell;
    }
    return null;
  }

  protected void expandModel(final FxRobot robot) {
    robot.interact(() -> getModelRootCell().getTreeItem().setExpanded(true));
  }

  public ZentaTreeCell getModelRootCell() {
    return findCellFor(rootFolder);
  }

}
