package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.magwas.zenta3.xml.Xpather;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ThingTest extends ZentaElementTest {

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    element = thing;
    name = ZentaElement.THING_NAME;
    documentation = ZentaElement.THING_DOCUMENTATION;
    ancestor = thing;
  }

  @Test
  public void a_serialized_model_contains_the_thing()
      throws ParserConfigurationException, SAXException, IOException,
      TransformerException {
    final Element theNode =
        new Xpather(xmlDocument).getElementById(ZentaElement.THING);
    assertEquals(ZentaElement.THING, theNode.getAttribute("id"));
  }

  @Test
  public void a_model_contains_a_thing_element() {
    assertEquals(
        ZentaElement.THING, model.getElementById(ZentaElement.THING).getId()
    );
  }

  @Test
  public void a_thing_is_a_thing() {
    final ZentaElement thing2 = model.getElementById(ZentaElement.THING);
    assertEquals(thing, thing2);
  }

  @Test
  public void a_random_element_is_not_a_thing() {
    assertNotEquals(thing, model.createElement(rootFolder, thing));
  }

  @Test
  public void thing_is_a_ZentaElement() {
    assertTrue(
        model.getElementById(ZentaElement.THING) instanceof ZentaElement
    );
  }

  @Test
  public void the_name_of_the_new_thing_is_Thing() {
    assertEquals(ZentaElement.THING_NAME, thing.getName());
  }

}
