package org.rulez.demokracia.zenta3.editor.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.rulez.demokracia.zenta3.model.ZentaElement;

public class AbstractEditorModelItem {

  protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  private ZentaElement zentaElement;

  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    pcs.addPropertyChangeListener(listener);
  }

  public void
      removePropertyChangeListener(final PropertyChangeListener listener) {
    pcs.removePropertyChangeListener(listener);
  }

  public ZentaElement getZentaElement() {
    return zentaElement;
  }

  public void setZentaElement(final ZentaElement zentaElement) {
    this.zentaElement = zentaElement;
  }

  public String getDocumentation() {
    return getZentaElement().getDocumentation();
  }

  public String getTitle() {
    return getZentaElement().getName();
  }

}
