package org.rulez.demokracia.zenta3.editor.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.rulez.demokracia.zenta3.model.Representation;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;

public class TreeRootElement implements ZentaElement {

  @Override
  public String getId() {
    return "TreeRoot";
  }

  @Override
  public ZentaElement getAncestor() {
    return null;
  }

  @Override
  public Object adapt(final Class<? extends Object> klass) {
    return null;
  }

  @Override
  public ZentaModel getModel() {
    return null;
  }

  @Override
  public String getName() {
    return "Models";
  }

  @Override
  public void setName(final String name) {
  }

  @Override
  public Representation getRepresentation() {
    return null;
  }

  @Override
  public void setRepresentation(final Representation representation) {

  }

  @Override
  public String getDocumentation() {
    return null;
  }

  @Override
  public void setDocumentation(final String documentation) {
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
  }

  @Override
  public void
      removePropertyChangeListener(final PropertyChangeListener listener) {
  }

  @Override
  public void firePropertyChange(final PropertyChangeEvent event) {
  }
}
