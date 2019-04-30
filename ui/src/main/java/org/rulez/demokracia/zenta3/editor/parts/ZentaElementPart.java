package org.rulez.demokracia.zenta3.editor.parts;

import org.rulez.demokracia.zenta3.model.ZentaElement;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ZentaElementPart extends Rectangle {

  private static final double DEFAULT_WIDTH = 50;
  private static final double DEFAULT_HEIGHT = 50;
  private static final Paint DEFAULT_STROKE = Color.AQUA;
  private final ZentaElement element;

  public ZentaElementPart(final ZentaElement element) {
    this.element = element;
    setWidth(DEFAULT_WIDTH);
    setHeight(DEFAULT_HEIGHT);
    setStroke(DEFAULT_STROKE);
  }

}
