package org.rulez.demokracia.zenta3.testhelpers;

import org.junit.jupiter.api.BeforeEach;
import org.rulez.demokracia.testhelpers.ThrowableTester;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.w3c.dom.Document;

public class CoreTestTools extends ThrowableTester {

  protected ZentaModel model;
  protected ZentaElement thing;
  protected ZentaElement element;
  protected Document xmlDocument;
  protected Folder rootFolder;
  protected String name;
  protected String documentation;
  protected ZentaElement ancestor;
  protected XmlConverter xmlUtils;

  public CoreTestTools() {
    super();
    xmlUtils = new XmlConverter(null);
  }

  @BeforeEach
  public void setUp() {
    model = ZentaModel.create();
    rootFolder = model.getRootFolder();
    xmlDocument = model.getXml();
    thing = model.getElementById(ZentaElement.THING);
    element = model.createElement(rootFolder, thing);
    name = "A test element";
    element.setName(name);
    documentation = "Documentation of the test element";
    element.setDocumentation(documentation);
    ancestor = thing;

  }

}
