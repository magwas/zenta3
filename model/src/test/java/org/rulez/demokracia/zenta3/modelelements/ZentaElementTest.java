package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.Representation;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.testhelpers.RelationTestTools;
import org.rulez.magwas.zenta3.xml.Xpather;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ZentaElementTest extends RelationTestTools {

  private static final String INPUT_STRING =
      "<graph xmlns=\"http://example.com\"><hello>a</hello></graph>";

  @Test
  public void a_serialized_model_contains_the_element()
      throws ParserConfigurationException, SAXException, IOException,
      TransformerException {
    final Element theNode =
        new Xpather(xmlDocument).getElementById(element.getId());
    assertEquals(element.getId(), theNode.getAttribute("id"));
  }

  @Test
  public void the_ancestor_is_the_element_which_with_we_created_our_element() {
    assertEquals(ancestor, element.getAncestor());
  }

  @Test
  public void an_element_is_itself() {
    final ZentaElement element2 = model.getElementById(element.getId());
    assertEquals(element, element2);
  }

  @Test
  public void for_an_unknown_id_getElementBy_returns_null() {
    assertEquals(null, model.getElementById("unknown"));
  }

  @Test
  public void the_name_of_an_element_can_be_get() {
    assertEquals(name, element.getName());
  }

  @Test
  public void the_name_of_an_element_is_in_its_string_representation() {
    final String elementString = element.toString();
    assertTrue(elementString.contains(name));
  }

  @Test
  public void the_documentation_of_an_element_can_be_get() {
    assertEquals(documentation, element.getDocumentation());
  }

  @Test
  public void the_id_of_an_element_is_in_its_string_representation() {
    assertTrue(element.toString().contains(element.getId()));
  }

  @Test
  public void the_representation_of_an_element_can_be_get() {
    assertTrue(element.getRepresentation() instanceof Representation);
  }

  @Test
  public void the_representation_of_an_element_can_be_set() {

    String representationString = INPUT_STRING;
    representationString =
        xmlUtils.xmlToString(xmlUtils.stringToXml(representationString));
    representationString =
        xmlUtils.xmlToString(xmlUtils.stringToXml(representationString));
    final Representation representation =
        new RepresentationImplementation(INPUT_STRING);

    element.setRepresentation(representation);
    assertEquals(
        representationString,
        xmlUtils.xmlToString(element.getRepresentation().asXml())
    );
  }

  @Test
  public void adapt_returns_null_for_unknown_class() {
    assertNull(element.adapt(Thing.class));
  }
}
