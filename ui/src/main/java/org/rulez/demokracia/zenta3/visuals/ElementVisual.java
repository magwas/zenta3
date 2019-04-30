package org.rulez.demokracia.zenta3.visuals;

import javafx.geometry.Orientation;
import javafx.scene.layout.Region;

public class ElementVisual extends Region {

  public ElementVisual() {
    super();
  }

  @Override
  public double computeMinHeight(final double width) {
    return 0;
  }

  @Override
  public double computeMinWidth(final double height) {
    return 0;
  }

  @Override
  protected double computePrefHeight(final double width) {
    return 0;
  }

  @Override
  protected double computePrefWidth(final double height) {
    return 0;
  }

  @Override
  public Orientation getContentBias() {
    return null;
  }

  public void setTitle(final String title) {
  }
}
