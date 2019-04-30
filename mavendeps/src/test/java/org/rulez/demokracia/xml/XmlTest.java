package org.rulez.demokracia.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.xpath.XPathConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.testhelpers.ThrowableTester;
import org.rulez.magwas.errors.ReportedError;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.rulez.magwas.zenta3.xml.Xpather;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlTest extends ThrowableTester {

  private XmlConverter converter;
  private String xmlString;
  private Document xml;
  private Xpather xpather;

  @BeforeEach
  public void setUp() {
    converter = new XmlConverter(XmlConverter.getResource());
    xmlString =
        "<module><element id=\"4\"/></module>";
    xml = converter.stringToXml(xmlString);
    xpather = new Xpather(xml);
  }

  @Test
  public void a_string_can_be_converted_to_xml_and_back() {
    xmlString = converter.xmlToString(xml);
    final String result = converter.xmlToString(xml);
    assertEquals(xmlString, result);
  }

  @Test
  public void bad_xml_string_cannot_converted_to_xml() {
    final String xmlString = "<modules><element id=\"4\"/></module>";
    assertThrows(
        () -> converter.stringToXml(xmlString)
    ).assertException(ReportedError.class)
        .assertMessageIs(
            "cannot convert String to XML: The element type \"modules\" must be terminated by the matching end-tag \"</modules>\"."
        );
  }

  @Test
  public void bad_xml_cannot_converted_to_string() {
    assertThrows(
        () -> converter.xmlToString(null)
    ).assertException(ReportedError.class)
        .assertMessageIs(
            "cannot convert xml to string: DOMSender: no start node defined"
        );
  }

  @Test
  public void getElementById_works() {
    final Element element = xpather.getElementById("4");
    assertEquals("4", element.getAttribute("id"));
  }

  @Test
  public void bad_xpath_results_in_error() {
    assertThrows(
        () -> xpather.evaluateXpathWithVariables(
            "//myns:foo::noSuchThing", XPathConstants.NODE, new HashMap<>()
        )
    ).assertException(ReportedError.class);
  }

  @Test
  public void createDocument_creates_document() throws MalformedURLException {
    final Document doc = converter.createDocument();
    assertEquals(null, doc.getDoctype());
  }

  @Test
  public void createDocument_creates_document_without_schema()
      throws MalformedURLException {
    final XmlConverter otherConverter = new XmlConverter(null);
    final Document doc = otherConverter.createDocument();
    assertEquals(null, doc.getDoctype());
  }

  @Test
  public void bad_resource_throws_exception() {
    final URL resource = XmlConverter.getResource("badschema.xsd");
    assertThrows(() -> new XmlConverter(resource))
        .assertException(ReportedError.class);
  }

  @Test
  public void can_convert_to_another_xml() {
    final Document otherXml = converter.nodeToXml(xml.getFirstChild());
    final String thisDocument = converter.xmlToString(xml);
    final String thatDocument = converter.xmlToString(otherXml);
    assertEquals(thisDocument, thatDocument);
  }

}
