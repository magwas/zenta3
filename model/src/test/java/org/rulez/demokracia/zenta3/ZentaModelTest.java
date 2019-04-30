package org.rulez.demokracia.zenta3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ListIterator;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.demokracia.zenta3.modelelements.ZentaElementImpl;
import org.rulez.demokracia.zenta3.testhelpers.NonCanonicalZentaElement;
import org.rulez.demokracia.zenta3.testhelpers.RelationTestTools;
import org.rulez.magwas.errors.ReportedError;
import org.w3c.dom.Element;

public class ZentaModelTest extends RelationTestTools {

  private class NonExistingClass extends ZentaElementImpl {

  }

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
  }

  @Test
  public void test_bad_class_to_create_throws_an_exception() {
    assertThrows(
        () -> ((ElementCreator) model)
            .createElementOfClass(NonExistingClass.class)
    )
        .assertException(ReportedError.class);
  }

  @Test
  public void the_model_name_is_kept_across_persistence()
      throws IOException, XPathExpressionException {
    final Path modelFile = Paths.get("/tmp/foo.zenta3");
    model.getRootFolder().setName("Model root");
    model.saveModel(modelFile);
    final ZentaModel model2 = ZentaModel.loadFile(modelFile);

    assertEquals("Model root", model2.getRootFolder().getName());
  }

  @Test
  public void the_model_can_be_saved_and_loaded()
      throws IOException, XPathExpressionException {
    final Path modelFile = Paths.get("/tmp/foo.zenta3");
    model.saveModel(modelFile);
    final ZentaModel model2 = ZentaModel.loadFile(modelFile);

    final Path model2File = Paths.get("/tmp/bar.zenta3");
    model2.saveModel(model2File);

    assertModelEquals(model, modelFile, model2, model2File);
  }

  @Test
  public void loading_unexisting_model_leads_to_an_exception() {
    final Path modelFile = Paths.get("/etc/foo.zenta3");
    assertThrows(() -> ZentaModel.loadFile(modelFile))
        .assertException(ReportedError.class);
  }

  @Test
  public void saving_error_leads_to_an_exception() {
    final Path modelFile = Paths.get("/tmp/foo.zenta3");
    model.saveModel(modelFile);
    final Path badModelFile = Paths.get("/etc/foo.zenta3");
    assertThrows(() -> model.saveModel(badModelFile))
        .assertException(ReportedError.class);
  }

  @Test
  public void getRootFolder_calls_results_in_the_same_object() {
    final Folder rf1 = model.getRootFolder();
    final Folder rf2 = model.getRootFolder();
    assertEquals(rf1, rf2);
  }

  @Test
  public void creating_bad_element_results_in_error() {
    final ElementCreator ec = new ElementCreator();
    final Element elementNode = model.getXml().createElement("element");
    final String elementId = "bad";
    assertThrows(
        () -> ec.constructElement(
            elementNode, elementId, NonCanonicalZentaElement.class
        )
    )
        .assertException(ReportedError.class);
  }

  public void assertModelEquals(
      final ZentaModel model1, final Path modelFile, final ZentaModel model2,
      final Path model2File
  ) throws FileNotFoundException, IOException {
    final String data1 = loadFileToString(modelFile);
    final String data2 = loadFileToString(model2File);
    assertEquals(data1, data2);
    assertModelElementEquals(model1.getRootFolder(), model2.getRootFolder());
  }

  public String loadFileToString(final Path modelFile)
      throws FileNotFoundException, IOException {
    final InputStream fis = Files.newInputStream(modelFile);
    return IOUtils.toString(fis, "UTF-8");
  }

  private void assertModelElementEquals(
      final ZentaElement element1, final ZentaElement element2
  ) {
    if (null == element1 || null == element2)
      fail(String.format("not equal:\n\t%s,\n\t%s\n", element1, element2));
    assertElementPropertiesMatch(element1, element2);
    if (element1 instanceof Folder)
      assertFoldersAreTheSame(element1, element2);
  }

  public void assertElementPropertiesMatch(
      final ZentaElement element1, final ZentaElement element2
  ) {
    assertEquals(element1.getClass(), element2.getClass());
    assertEquals(element1.getId(), element2.getId());
    assertEquals(element1.getName(), element2.getName());
    final String ancestor1 = element1.getAncestor().getId();
    final String ancestor2 = element2.getAncestor().getId();
    assertEquals(ancestor1, ancestor2);
    assertEquals(element1.getDocumentation(), element2.getDocumentation());
  }

  public void assertFoldersAreTheSame(
      final ZentaElement element1, final ZentaElement element2
  ) {
    final Folder folder1 = (Folder) element1;
    final Folder folder2 = (Folder) element2;
    final ListIterator<ZentaElement> list1 =
        folder1.getElements().listIterator();
    final ListIterator<ZentaElement> list2 =
        folder2.getElements().listIterator();
    while (list1.hasNext()) {
      final ZentaElement next = list1.next();
      assertModelElementEquals(next, list2.next());
    }
  }

}
