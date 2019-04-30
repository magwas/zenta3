package org.rulez.demokracia.zenta3.modelelements;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.Representation;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ZentaElementImpl implements ZentaElement {

  protected Element node;
  protected ZentaModel model;
  protected PropertyChangeSupport propertyChangeSupport =
      new PropertyChangeSupport(this);

  protected ZentaElementImpl() {
  }

  public ZentaElementImpl(final Folder folder) {
    model = folder.getModel();
    final Element folderNode = (Element) folder.adapt(Element.class);
    node = model.getXml().createElement("element");
    folderNode.appendChild(node);
    final String idValue = generateId();
    setNodeId(idValue);
  }

  public ZentaElementImpl(final Folder folder, final ZentaElement parent) {
    this(folder);
    setNodeAttribute("ancestor", parent.getId());
  }

  public ZentaElementImpl(final ZentaModel model, final Element elementNode) {
    this.model = model;
    node = elementNode;
  }

  @Override
  public void
      addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  @Override
  public void
      removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override
  public void firePropertyChange(final PropertyChangeEvent event) {
    propertyChangeSupport.firePropertyChange(event);
  }

  protected final void setNodeAttribute(final String name, final String value) {
    node.setAttribute(name, value);
  }

  protected final void setNodeId(final String value) {
    setNodeAttribute("id", value);
  }

  protected void setNodeBasicInfo(
      final String idValue, final String name, final String documentation
  ) {
    setNodeId(idValue);
    setNodeAttribute("name", name);
    setDocumentation(documentation);
  }

  @Override
  public String getId() {
    return node.getAttribute("id");
  }

  @Override
  public ZentaElement getAncestor() {
    final ZentaElement ancie =
        model.getElementById(node.getAttribute("ancestor"));
    if (null == ancie)
      return model.getElementById(ZentaElement.THING);
    return ancie;
  }

  @Override
  public Object adapt(final Class<? extends Object> klass) {
    if (klass.equals(Element.class))
      return node;
    return null;
  }

  @Override
  public ZentaModel getModel() {
    return model;
  }

  @Override
  public void setName(final String name) {
    setNodeAttribute("name", name);
  }

  @Override
  public String getName() {
    return node.getAttribute("name");
  }

  protected final String generateId() {
    return RandomStringUtils.randomAlphanumeric(10);
  }

  @Override
  public String toString() {
    return String
        .format("%s(id=%s,name=%s)", this.getClass(), getId(), getName());
  }

  @Override
  public Representation getRepresentation() {
    final Element storeNode =
        (Element) node.getElementsByTagName("representation").item(0);
    Document representationDoc;
    final XmlConverter xmlUtils = new XmlConverter(null);
    if (null == storeNode)
      representationDoc = xmlUtils.createDocument();
    else
      representationDoc = xmlUtils.nodeToXml(storeNode.getFirstChild());
    return new RepresentationImplementation(representationDoc);
  }

  @Override
  public void setRepresentation(final Representation representation) {
    final Element representationNode =
        representation.asXml().getDocumentElement();
    final Document doc = model.getXml();
    final Element adoptedElement = (Element) doc.adoptNode(representationNode);
    Element storeNode =
        (Element) node.getElementsByTagName("representation").item(0);
    if (null != storeNode)
      node.removeChild(storeNode);
    storeNode = doc.createElement("representation");
    storeNode.appendChild(adoptedElement);
    node.appendChild(storeNode);
  }

  @Override
  public String getDocumentation() {
    final NodeList documentations =
        node.getElementsByTagName(DOCUMENTATION_TAG_NAME);
    for (int i = 0; i < documentations.getLength(); i++) {
      final Node item = documentations.item(i);
      if (item.getParentNode().equals(node))
        return item.getTextContent();
    }
    return "";
  }

  @Override
  public void setDocumentation(final String documentation) {
    final NodeList documentations =
        node.getElementsByTagName(DOCUMENTATION_TAG_NAME);
    for (int i = 0; i < documentations.getLength(); i++) {
      final Node item = documentations.item(i);
      if (item.getParentNode().equals(node))
        node.removeChild(documentations.item(i));
    }
    final Document doc = model.getXml();
    final Element newDocumentation = doc.createElement(DOCUMENTATION_TAG_NAME);
    final Text textNode = doc.createTextNode(documentation);
    newDocumentation.appendChild(textNode);
    node.appendChild(newDocumentation);
  }
}
