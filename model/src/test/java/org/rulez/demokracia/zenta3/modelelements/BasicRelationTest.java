package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.ZentaElement;
import org.rulez.demokracia.zenta3.model.ZentaRelation;

public class BasicRelationTest extends RelationshipTest {

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    documentation = ZentaElement.BASIC_RELATION_DOCUMENTATION;
    element = basicRelation;
    name = "Basic Relation";
    ancestor = thing;
  }

  @Test
  public void a_model_contains_a_basic_relationship_element() {
    assertEquals(ZentaElement.BASIC_RELATION, basicRelation.getId());
  }

  @Test
  public void basic_relationship_is_a_relationship() {
    assertTrue(basicRelation instanceof ZentaRelation);
  }

  @Test
  public void source_of_basic_relation_is_thing() {
    assertEquals(thing, basicRelation.getSource());
  }

  @Test
  public void target_of_basic_relation_is_thing() {
    assertEquals(thing, basicRelation.getTarget());
  }

  @Test
  public void the_name_of_a_new_basic_relation_is_Basic_Relation() {
    assertEquals(ZentaElement.BASIC_RELATION_NAME, basicRelation.getName());
  }

}
