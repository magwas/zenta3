package org.rulez.magwas.zenta3.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;

public class MyNamespaceContext implements NamespaceContext {

  @Override
  public String getNamespaceURI(final String prefix) {
    return "http://magwas.rulez.org/zenta.3.0";
  }

  @Override
  public String getPrefix(final String uri) {
    return "zenta";
  }

  @Override
  public Iterator<String> getPrefixes(final String uri) {
    final List<String> prefixes = new ArrayList<>();
    prefixes.add("zenta");
    return prefixes.listIterator();
  }

}
