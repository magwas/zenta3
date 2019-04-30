package org.rulez.demokracia.zenta3.editor.model;

import java.util.List;

import org.eclipse.gef.geometry.planar.Rectangle;
import org.rulez.demokracia.zenta3.model.ZentaElement;

import com.google.common.collect.Lists;

import javafx.scene.paint.Color;

public class EditorModelElement extends AbstractEditorModelItem {

  public static final String PROP_TITLE = "title";

  public static final String PROP_DOCUMENTATION = "documentation";
  public static final String PROP_COLOR = "color";
  public static final String PROP_BOUNDS = "bounds";
  public static final String PROP_INCOMING_CONNECTIONS = "incomingConnections";
  public static final String PROP_OUTGOGING_CONNECTIONS = "outgoingConnections";
  private Color color;
  private Rectangle bounds;
  private final List<EditorModelConnection> incomingConnections =
      Lists.newArrayList();

  private final List<EditorModelConnection> outgoingConnections =
      Lists.newArrayList();

  protected EditorModelElement(final ZentaElement element) {
    super();
    setZentaElement(element);
  }

  public void addIncomingConnection(final EditorModelConnection conn) {
    incomingConnections.add(conn);
    pcs.firePropertyChange(PROP_INCOMING_CONNECTIONS, null, conn);
  }

  public void addOutgoingConnection(final EditorModelConnection conn) {
    outgoingConnections.add(conn);
    pcs.firePropertyChange(PROP_OUTGOGING_CONNECTIONS, null, conn);
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public Color getColor() {
    return color;
  }

  public List<EditorModelConnection> getIncomingConnections() {
    return incomingConnections;
  }

  public List<EditorModelConnection> getOutgoingConnections() {
    return outgoingConnections;
  }

  public void removeIncomingConnection(final EditorModelConnection conn) {
    incomingConnections.remove(conn);
    pcs.firePropertyChange(PROP_INCOMING_CONNECTIONS, conn, null);
  }

  public void removeOutgoingConnection(final EditorModelConnection conn) {
    outgoingConnections.remove(conn);
    pcs.firePropertyChange(PROP_OUTGOGING_CONNECTIONS, conn, null);
  }

  public void setBounds(final Rectangle bounds) {
    this.bounds = bounds.getCopy();
    pcs.firePropertyChange(PROP_BOUNDS, this.bounds, this.bounds);
  }

  public void setColor(final Color color) {
    this.color = color;
    pcs.firePropertyChange(PROP_COLOR, this.color, color);
  }

  public void setDocumentation(final String documentation) {
    final String oldDocumentation = getZentaElement().getDocumentation();
    getZentaElement().setDocumentation(documentation);
    pcs.firePropertyChange(PROP_DOCUMENTATION, oldDocumentation, documentation);
  }

  public void setTitle(final String title) {
    final String oldTitle = getZentaElement().getName();
    getZentaElement().setName(title);
    pcs.firePropertyChange(PROP_TITLE, oldTitle, title);
  }

}
