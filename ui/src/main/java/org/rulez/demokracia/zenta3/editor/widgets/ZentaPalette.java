package org.rulez.demokracia.zenta3.editor.widgets;

import org.rulez.demokracia.zenta3.model.ZentaModel;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ZentaPalette extends VBox {

  public ZentaPalette(final ZentaModel model, final ZentaCanvas canvas) {
    super();

    final Button createElement = new Button("New ELEMENT");
    createElement.setOnAction((e) -> canvas.addElement(e, null));

    final Button createConn = new Button("New CONNECTION");
    createConn.setMaxWidth(Double.MAX_VALUE);
    createConn.setMinHeight(50);

    setWidth(20);
    getChildren().add(createElement);
    getChildren().add(createConn);
  }

}
