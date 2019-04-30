package org.rulez.demokracia.zenta3.editor.model;

public abstract class AbstractEditorModelConnection
    extends AbstractEditorModelItem {

  protected EditorModelElement source;
  protected EditorModelElement target;

  public EditorModelElement getSource() {
    return source;
  }

  public EditorModelElement getTarget() {
    return target;
  }

  public void setSource(final EditorModelElement source) {
    this.source = source;
  }

  public void setTarget(final EditorModelElement target) {
    this.target = target;
  }

}
