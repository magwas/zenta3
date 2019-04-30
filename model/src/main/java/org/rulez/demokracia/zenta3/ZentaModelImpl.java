package org.rulez.demokracia.zenta3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.magwas.errors.ReportedError;

public class ZentaModelImpl extends ElementCreator
    implements ZentaModel, ZentaModelInterface {

  public ZentaModelImpl() {
    super();
    doc = xmlConverter.createDocument();
    populateBaseelements();
  }

  public ZentaModelImpl(final Path modelFile) {
    super();
    String data;
    try {
      final InputStream fis = Files.newInputStream(modelFile);
      data = IOUtils.toString(fis, "UTF-8");
    } catch (final IOException e) {
      throw new ReportedError("cannot load model file", e.getMessage());
    }
    doc = xmlConverter.stringToXml(data);
    populateBaseelements();
  }

  private void populateBaseelements() {
    for (final String baseElementId : constructorMap.keySet())
      getElementById(baseElementId);
  }

  @Override
  public void saveModel(final Path file) {
    try {
      final OutputStream writer = Files.newOutputStream(file);
      final String modelAsString = xmlConverter.xmlToString(doc);
      writer.write(modelAsString.getBytes());
      writer.close();
    } catch (final IOException e) {
      throw new ReportedError("Could not write model", e.getMessage());
    }
  }

}
