package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.ZentaElement;

public class DerivedElementTest extends ZentaElementTest {

  private ZentaElement derivedElement;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    derivedElement = model.createElement(rootFolder, element);

  }

  @Test
  public void representation_can_be_set_twice() {
    final RepresentationImplementation representation =
        new RepresentationImplementation("<rep>hehe</rep>");
    derivedElement.setRepresentation(representation);
    final String representationString = "<rep>haho</rep>";
    final String stringDoc =
        xmlUtils.xmlToString(xmlUtils.stringToXml(representationString));

    derivedElement.setRepresentation(
        new RepresentationImplementation(representationString)
    );

    assertEquals(
        stringDoc,
        xmlUtils.xmlToString(derivedElement.getRepresentation().asXml())
    );
  }

  @Test
  public void the_documentation_is_originally_empty() {
    assertEquals("", derivedElement.getDocumentation());
  }

}
