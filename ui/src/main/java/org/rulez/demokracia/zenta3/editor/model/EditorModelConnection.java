package org.rulez.demokracia.zenta3.editor.model;

import org.rulez.demokracia.zenta3.model.ZentaRelation;

public class EditorModelConnection extends AbstractEditorModelConnection {

  private boolean connected;

  public EditorModelConnection(final ZentaRelation relation) {
    super();
    setZentaElement(relation);
  }

  protected void connect(
      final EditorModelElement source, final EditorModelElement target
  ) {
    checkSourceAndTarget(source, target);
    disconnect();
    this.source = source;
    this.target = target;
    reconnect();
  }

  public void checkSourceAndTarget(
      final EditorModelElement source, final EditorModelElement target
  ) {
    checkNotNull(source);
    checkNotNull(target);
    if (source.equals(target))
      throw new IllegalArgumentException();
  }

  public void checkNotNull(final EditorModelElement source) {
    if (source == null)
      throw new IllegalArgumentException();
  }

  public void disconnect() {
    if (connected) {
      source.removeOutgoingConnection(this);
      target.removeIncomingConnection(this);
      connected = false;
    }
  }

  public void reconnect() {
    if (!connected) {
      source.addOutgoingConnection(this);
      target.addIncomingConnection(this);
      connected = true;
    }
  }

}
