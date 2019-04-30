package org.rulez.demokracia.zenta3.modelelements;

import org.rulez.demokracia.zenta3.model.Representation;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.w3c.dom.Document;

public class RepresentationImplementation implements Representation {

  final private Document doc;

  public RepresentationImplementation(final String representationString) {
    final XmlConverter xmlUtils = new XmlConverter(null);
    final Document representationDoc =
        xmlUtils.stringToXml(representationString);

    doc = representationDoc;
  }

  public RepresentationImplementation(final Document representationDoc) {
    doc = representationDoc;
  }

  @Override
  public Document asXml() {
    return doc;
  }
}
