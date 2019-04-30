package org.rulez.demokracia.zenta3.editor.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.rulez.demokracia.zenta3.editor.ZentaApplication;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeItem;
import org.rulez.demokracia.zenta3.editor.widgets.IInteractiveWidgets;
import org.rulez.demokracia.zenta3.editor.widgets.ZentaCanvas;
import org.rulez.demokracia.zenta3.editor.widgets.ZentaPalette;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ProvidesElements;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;

import com.google.inject.Inject;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class EditorModelFactory {

  @Inject
  public static IInteractiveWidgets widgetFactory;

  public static EditorModelItem currentModel;
  private static List<EditorModelItem> editorModels = new ArrayList<>();

  public static void saveAction(final Stage stage) {
    if (!currentModel.havePath()) {
      final String title = "Save as...";
      final File file = widgetFactory.chooseFileToSave(title);
      if (null == file)
        return;
      currentModel.setPath(file.toPath());
    }
    currentModel.save();
  }

  public static void loadAction(final Stage stage) {
    final String title = "Load model...";
    final File modelFile = widgetFactory.chooseFileToLoad(title);
    final ZentaModel model = ZentaModel.loadFile(modelFile.toPath());
    loadModel(model);
  }

  public static void loadModel(final ZentaModel model) {
    if (isModelAlreadyLoaded(model))
      return;
    final Folder rootFolder = addModelToTreeView(model);
    final ZentaCanvas canvas = addEditorTabFor(rootFolder);
    changeCurrentModel(model, canvas);
  }

  private static ZentaCanvas addEditorTabFor(final Folder rootFolder) {
    final TabPane tabPane = ZentaApplication.getTabPane();
    final Tab tab = new Tab();
    final ZentaCanvas canvas = new ZentaCanvas(rootFolder);
    tab.setContent(canvas);
    tab.setText(rootFolder.getName());
    tabPane.getTabs().add(tab);
    return canvas;
  }

  private static Folder addModelToTreeView(final ZentaModel model) {
    final TreeView<ZentaElement> treeView = ZentaApplication.getTreeView();
    final Folder rootFolder = model.getRootFolder();
    treeView.getRoot().getChildren().add(new ZentaTreeItem(rootFolder));
    return rootFolder;
  }

  private static void
      changeCurrentModel(final ZentaModel model, final ZentaCanvas canvas) {
    currentModel = new EditorModelItem(model);
    ZentaApplication.setPalette(new ZentaPalette(model, canvas));
    editorModels.add(currentModel);
  }

  private static boolean isModelAlreadyLoaded(final ProvidesElements model) {
    for (final EditorModelItem aModel : editorModels)
      if (
        model.getRootFolder().getId()
            .equals(aModel.getModel().getRootFolder().getId())
      )
        return true;
    return false;
  }

  public static List<EditorModelItem> getModels() {
    return editorModels;
  }

}
