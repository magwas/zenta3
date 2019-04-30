package org.rulez.magwas.zenta3.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.rulez.magwas.errors.ReportedError;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlConverter extends XmlFactoryBuilder {

  public static final String EMPTY_ATTRIBUTE_VALUE = "";
  public static final String XML_SCHEMA = "http://magwas.rulez.org/zenta.3.0";

  public XmlConverter(final URL schemaResource) {
    super(schemaResource);
  }

  public Document createDocument() {
    DocumentBuilder builder;
    try {
      builder = factory.newDocumentBuilder();
      return builder.newDocument();
    } catch (final ParserConfigurationException e) {
      throw new ReportedError("cannot create new document", e.getMessage());
    }
  }

  public Document stringToXml(final String modelString) {
    DocumentBuilder builder;
    Document xmlDocument;
    try {
      builder = factory.newDocumentBuilder();
      final byte[] uTF8Bytes =
          modelString.getBytes(StandardCharsets.UTF_8);
      final ByteArrayInputStream inputStream =
          new ByteArrayInputStream(uTF8Bytes);
      xmlDocument = builder.parse(inputStream);
      return xmlDocument;
    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new ReportedError("cannot convert String to XML", e.getMessage());
    }
  }

  public Document nodeToXml(final Node node) {
    final Document doc = stringToXml(xmlToString(node));
    doc.normalizeDocument();
    return doc;
  }

  public String xmlToString(final Node doc) {
    final TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
    Transformer transformer;
    try {
      transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
      final StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(doc), new StreamResult(writer));
      return writer.getBuffer().toString();
    } catch (final TransformerException e) {
      throw new ReportedError("cannot convert xml to string", e.getMessage());
    }
  }

  public static URL getResource() {
    final String fileName = "zenta.xsd";
    return getResource(fileName);
  }

  public static URL getResource(final String fileName) {
    return Thread.currentThread().getContextClassLoader()
        .getResource(fileName);
  }

}
