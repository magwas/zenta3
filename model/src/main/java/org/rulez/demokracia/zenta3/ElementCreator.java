package org.rulez.demokracia.zenta3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.rulez.demokracia.zenta3.model.Folder;
import org.rulez.demokracia.zenta3.model.ProvidesElements;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaModel;
import org.rulez.demokracia.zenta3.modelelements.BasicRelation;
import org.rulez.demokracia.zenta3.modelelements.IsARelation;
import org.rulez.demokracia.zenta3.modelelements.MetaFolder;
import org.rulez.demokracia.zenta3.modelelements.RelationImpl;
import org.rulez.demokracia.zenta3.modelelements.Thing;
import org.rulez.demokracia.zenta3.modelelements.ZentaElementImpl;
import org.rulez.magwas.errors.ReportedError;
import org.rulez.magwas.zenta3.xml.XmlConverter;
import org.rulez.magwas.zenta3.xml.Xpather;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ElementCreator implements ProvidesElements {

  protected Document doc;
  protected XmlConverter xmlConverter;
  private final Map<String, ZentaElement> catalog;
  protected final Map<String,
      Class<? extends Object>> constructorMap = Map.of(
          ZentaElement.THING, Thing.class,
          ZentaElement.BASIC_RELATION, BasicRelation.class,
          ZentaElement.IS_A_RELATION, IsARelation.class,
          ZentaElement.FOLDER, MetaFolder.class
      );

  protected ElementCreator() {
    xmlConverter = new XmlConverter(XmlConverter.getResource());
    catalog = new HashMap<>();
  }

  @Override
  public Document getXml() {
    return doc;
  }

  @Override
  public ZentaElement catalogize(final ZentaElement zentaElement) {
    final String elementId = zentaElement.getId();
    if (catalog.containsKey(elementId))
      return catalog.get(elementId);
    catalog.put(elementId, zentaElement);
    return zentaElement;
  }

  @Override
  public final ZentaElement getElementById(final String elementId) {
    if (catalog.containsKey(elementId))
      return catalog.get(elementId);
    final Element elementNode = new Xpather(doc).getElementById(elementId);
    if (null != elementNode)
      return introduceElementFromXml(elementNode, elementId);
    return introduceCoreElement(elementId);
  }

  @SuppressWarnings("unchecked")
  public ZentaElement introduceElementFromXml(
      final Element elementNode, final String elementId
  ) {
    Class<? extends ZentaElement> klass =
        (Class<? extends ZentaElement>) constructorMap.get(elementId);
    if (null == klass)
      klass = chooseClassBasedOnWhetherItIsARelation(elementNode);
    return constructElement(elementNode, elementId, klass);
  }

  private Class<? extends ZentaElement>
      chooseClassBasedOnWhetherItIsARelation(final Element elementNode) {
    Class<? extends ZentaElement> klass;
    if (
      XmlConverter.EMPTY_ATTRIBUTE_VALUE
          .equals(elementNode.getAttribute(ZentaElement.SOURCE_ATTRIBUTE))
    )
      klass = ZentaElementImpl.class;
    else
      klass = RelationImpl.class;
    return klass;
  }

  @SuppressWarnings("unchecked")
  private ZentaElement introduceCoreElement(final String elementId) {
    final Class<? extends ZentaElement> klass =
        (Class<? extends ZentaElement>) constructorMap.get(elementId);
    if (null != klass)
      return catalogize(createElementOfClass(klass));
    return null;
  }

  protected ZentaElement
      createElementOfClass(final Class<? extends ZentaElement> klass) {
    try {
      final Constructor<? extends ZentaElement> constructor =
          klass.getConstructor(Folder.class);
      return constructor.newInstance(getRootFolder());
    } catch (
        InstantiationException | IllegalAccessException |
        IllegalArgumentException | InvocationTargetException |
        NoSuchMethodException | SecurityException e
    ) {
      throw new ReportedError("internal error", e.getMessage());
    }
  }

  protected ZentaElement constructElement(
      final Element elementNode, final String elementId,
      final Class<? extends ZentaElement> klass
  ) {
    try {
      final Constructor<? extends ZentaElement> constructor =
          klass.getConstructor(ZentaModel.class, Element.class);
      return constructor.newInstance(this, elementNode);
    } catch (
        NoSuchMethodException | SecurityException | InstantiationException |
        IllegalAccessException | IllegalArgumentException |
        InvocationTargetException e
    ) {
      throw new ReportedError(
          "internal error at loading", e.getMessage() + ": " + elementId
      );
    }
  }

}
