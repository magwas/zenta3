package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.ZentaElement;

public class IsARelationTest extends RelationshipTest {

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    element = theIsARelation;
    name = ZentaElement.IS_A_RELATION_NAME;
    documentation = ZentaElement.IS_A_RELATION_DOCUMENTATION;
    ancestor = basicRelation;
  }

  @Test
  public void a_model_contains_an_isA_relationship() {
    assertEquals(ZentaElement.IS_A_RELATION, theIsARelation.getId());
  }

  @Test
  public void isA_is_an_isA() {
    assertTrue(theIsARelation instanceof IsARelation);
  }

  @Test
  public void source_of_isA_relation_is_thing() {
    assertEquals(thing, theIsARelation.getSource());
  }

  @Test
  public void target_of_isA_relation_is_thing() {
    assertEquals(thing, theIsARelation.getTarget());
  }

  @Test
  public void the_name_of_a_new_isA_relation_is_IsA_Relation() {
    assertEquals(ZentaElement.IS_A_RELATION_NAME, theIsARelation.getName());
  }

}
