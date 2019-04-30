package org.rulez.demokracia.zenta3.testhelpers;

import org.junit.jupiter.api.BeforeEach;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaRelation;

public class RelationTestTools extends CoreTestTools {

  protected ZentaRelation basicRelation;
  protected ZentaRelation theIsARelation;
  protected ZentaElement sourceElement;
  protected ZentaElement targetElement;
  protected ZentaRelation newRelation;
  protected String newRelationName;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    basicRelation =
        (ZentaRelation) model.getElementById(ZentaElement.BASIC_RELATION);
    theIsARelation =
        (ZentaRelation) model.getElementById(ZentaElement.IS_A_RELATION);
    sourceElement = model.createElement(rootFolder, thing);
    targetElement = model.createElement(rootFolder, thing);
    newRelation = model.createRelation(
        rootFolder, basicRelation, sourceElement, targetElement
    );
    newRelationName = "Test Relation";
    newRelation.setName(newRelationName);
  }

}
