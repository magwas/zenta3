package org.rulez.demokracia.zenta3.editor.model;

import java.nio.file.Path;
import java.util.List;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.demokracia.zenta3.model.ZentaRelation;

import com.google.common.collect.Lists;

public class EditorModelItem extends AbstractEditorModelItem {

  public static final String PROP_CHILD_ELEMENTS = "childElements";

  private final List<AbstractEditorModelItem> childElements =
      Lists.newArrayList();

  private ZentaModel model;

  private Path path;

  public EditorModelItem(final ZentaModel model) {
    super();
    this.model = model;
  }

  public void addChildElement(final AbstractEditorModelItem element) {
    childElements.add(element);
    pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, element);
  }

  public void
      addChildElement(final AbstractEditorModelItem element, final int idx) {
    childElements.add(idx, element);
    pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, element);
  }

  public EditorModelConnection createConnectionBetween(
      final EditorModelElement child,
      final EditorModelElement child2
  ) {
    final ZentaElement element = child.getZentaElement();
    final ZentaElement element2 = child2.getZentaElement();
    final ZentaRelation relation = createZentaConnection(element, element2);
    final EditorModelConnection conn = new EditorModelConnection(relation);
    conn.connect(child, child2);
    return conn;
  }

  public EditorModelElement createEditorModelElement() {
    return new EditorModelElement(createZentaElement());
  }

  private ZentaRelation createZentaConnection(
      final ZentaElement source, final ZentaElement target
  ) {
    final ZentaModel model = getModel();
    final Folder folder = model.getRootFolder();
    final ZentaElement parent =
        model.getElementById(ZentaElement.BASIC_RELATION);
    return model.createRelation(folder, parent, source, target);
  }

  private ZentaElement createZentaElement() {
    final ZentaModel model = getModel();
    final Folder folder = model.getRootFolder();
    final ZentaElement parent = model.getElementById(ZentaElement.THING);
    return model.createElement(folder, parent);
  }

  public List<AbstractEditorModelItem> getChildElements() {
    return childElements;
  }

  public ZentaModel getModel() {
    return model;
  }

  public void removeChildElement(final AbstractEditorModelItem element) {
    childElements.remove(element);
    pcs.firePropertyChange(PROP_CHILD_ELEMENTS, element, null);
  }

  public void setModel(final ZentaModel model) {
    this.model = model;
  }

  public void setPath(final Path path) {
    this.path = path;
  }

  public boolean havePath() {
    return path != null;
  }

  public void save() {
    getModel().saveModel(path);
  }
}
