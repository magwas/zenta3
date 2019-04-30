package org.rulez.magwas.zenta3.xml;

import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.rulez.magwas.errors.ReportedError;
import org.xml.sax.SAXException;

public class XmlFactoryBuilder {

  protected final DocumentBuilderFactory factory;

  protected XmlFactoryBuilder(final URL schemaResource) {
    factory = DocumentBuilderFactory.newInstance();
    if (null != schemaResource) {
      final Schema compiledSchema = createSchema(schemaResource);
      factory.setSchema(compiledSchema);
      factory.setValidating(false);
      factory.setNamespaceAware(true);
    }
  }

  protected Schema createSchema(final URL schemaResource) {
    final SchemaFactory schemaFactory =
        SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    Schema compiledSchema;
    try {
      compiledSchema = schemaFactory.newSchema(schemaResource);
    } catch (final SAXException e) {
      throw new ReportedError("cannot load zenta schema", e.getMessage());
    }
    return compiledSchema;
  }

}
