package org.rulez.demokracia.zenta3.editor;

import org.rulez.demokracia.zenta3.editor.model.EditorModelFactory;
import org.rulez.demokracia.zenta3.editor.tree.TreeRoot;
import org.rulez.demokracia.zenta3.editor.tree.ZentaTreeView;
import org.rulez.demokracia.zenta3.editor.widgets.InteractiveWidgets;
import org.rulez.demokracia.zenta3.editor.widgets.ZentaPalette;
import org.rulez.demokracia.zenta3.model.ZentaElement;

import com.google.inject.Guice;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ZentaApplication extends Application {

  public static BorderPane mainPane;
  public static DomainHandler domainHandler;
  private Stage stage;

  public static void run(final Object stuff) {
    final String[] args = new String[0];
    Application.launch(args);
  }

  private Node createButtonBar() {
    final Button saveButton = new Button("Save");
    saveButton.setOnAction((e) -> EditorModelFactory.saveAction(stage));

    final Button loadButton = new Button("Load");
    loadButton.setOnAction((e) -> EditorModelFactory.loadAction(stage));

    final Button undoButton = new Button("Undo");
    undoButton.setDisable(true);

    final Button redoButton = new Button("Redo");
    redoButton.setDisable(true);

    domainHandler.setButtons(undoButton, redoButton);

    return new HBox(10, loadButton, saveButton, undoButton, redoButton);
  }

  public BorderPane hookViewers() {
    final BorderPane pane = new BorderPane();

    final TreeView<ZentaElement> treeView = new ZentaTreeView();
    final TreeRoot root = new TreeRoot();
    treeView.setRoot(root);
    pane.setLeft(treeView);
    pane.setTop(createButtonBar());
    final TabPane tabPane = new TabPane();
    pane.setCenter(tabPane);

    pane.setMinWidth(800);
    pane.setMinHeight(600);
    return pane;
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    stage = primaryStage;

    initializeData();

    final Scene scene = new Scene(mainPane);
    scene.getStylesheets().add("zenta3.css");
    stage.setScene(scene);

    stage.setResizable(true);
    stage.setTitle("Zenta Model");
    stage.sizeToScene();
    stage.show();
  }

  public DomainHandler initializeData() {
    final ZentaEditorModule module = new ZentaEditorModule();
    Guice.createInjector(module);
    InteractiveWidgets.setStage(stage);
    domainHandler = new DomainHandler();
    mainPane = hookViewers();
    return domainHandler;
  }

  @SuppressWarnings("unchecked")
  public static TreeView<ZentaElement> getTreeView() {
    return (TreeView<ZentaElement>) mainPane.getLeft();
  }

  public static TabPane getTabPane() {
    return (TabPane) mainPane.getCenter();
  }

  public static void setPalette(final ZentaPalette palette) {
    mainPane.setRight(palette);
  }

}
