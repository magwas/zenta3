package org.rulez.demokracia.zenta3.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

public interface ZentaElement extends Serializable {

  String THING = "thing";
  String THING_NAME = "Thing";
  String THING_DOCUMENTATION = "A thing is a thing is a thing";

  String BASIC_RELATION = "basicrelation";
  String BASIC_RELATION_NAME = "Basic Relation";
  String BASIC_RELATION_DOCUMENTATION =
      "Denotes some kind of connection between two things";

  String IS_A_RELATION = "is_a";
  String IS_A_RELATION_NAME = "IsA_Relation";
  String IS_A_RELATION_DOCUMENTATION =
      "A relation denoting that a thing is a kind of other thing";

  String FOLDER = "folder";
  String FOLDER_NAME = "Folder";
  String FOLDER_DOCUMENTATION =
      "A thing which groups other things conceptionally";

  String DOCUMENTATION_TAG_NAME = "documentation";
  String ANCESTOR_ATTRIBUTE = "ancestor";
  String ID_ATTRIBUTE = "id";
  String SOURCE_ATTRIBUTE = "source";

  String getId();

  ZentaElement getAncestor();

  Object adapt(final Class<? extends Object> klass);

  ZentaModel getModel();

  String getName();

  void setName(final String name);

  Representation getRepresentation();

  void setRepresentation(final Representation representation);

  String getDocumentation();

  void setDocumentation(String documentation);

  void addPropertyChangeListener(PropertyChangeListener listener);

  void removePropertyChangeListener(PropertyChangeListener listener);

  void firePropertyChange(PropertyChangeEvent event);

}
