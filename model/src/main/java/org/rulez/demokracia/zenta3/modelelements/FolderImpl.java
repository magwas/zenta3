package org.rulez.demokracia.zenta3.modelelements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPathConstants;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.rulez.magwas.zenta3.xml.Xpather;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class FolderImpl extends ZentaElementImpl implements Folder {

  public FolderImpl(final ZentaModel zentaModel) {//NOPMD[CallSuperInConstructor]
    model = zentaModel;
    final Document doc = model.getXml();
    final Element documentElement = doc.getDocumentElement();
    if (null == documentElement) {
      node = doc.createElementNS(XmlConverter.XML_SCHEMA, "model");
      doc.appendChild(node);
    } else
      node = documentElement;
    if ("".equals(node.getAttribute("id")))
      setNodeId(generateId());
    setNodeAttribute("ancestor", "folder");
    if ("".equals(node.getAttribute("name")))
      setNodeAttribute("name", "Model");
  }

  public FolderImpl(final ZentaModel model, final Element elementNode) {
    super(model, elementNode);
  }

  public FolderImpl(final Folder parentFolder) {
    super(parentFolder);
    setNodeAttribute("ancestor", "folder");
  }

  @Override
  public List<ZentaElement> getElements() {
    final Xpather xmlConverter = new Xpather(node);
    final NodeList childElementNodes =
        (NodeList) xmlConverter.evaluateXpathWithVariables(
            "element",
            XPathConstants.NODESET, new HashMap<>()
        );
    final List<ZentaElement> children = new ArrayList<>();
    for (int i = 0; i < childElementNodes.getLength(); i++) {
      final Element item = (Element) childElementNodes.item(i);
      final ZentaElement zentaElement =
          model.getElementById(item.getAttribute(ZentaElement.ID_ATTRIBUTE));
      children.add(zentaElement);
    }
    return children;
  }

}
