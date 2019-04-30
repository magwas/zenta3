package org.rulez.demokracia.zenta3.model;

import java.util.List;

public interface Folder extends ZentaElement {

  String CHILDREN_PROPERTY = "CHILDREN";

  List<ZentaElement> getElements();

}
