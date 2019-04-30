package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.ZentaRelation;
import org.rulez.magwas.errors.ReportedError;

public class RelationshipTest extends ZentaElementTest {

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    element = newRelation;
    name = newRelationName;
    ancestor = basicRelation;
    name = "Test Relation";
    element.setName(name);
    documentation = "Documentation of test relation";
    element.setDocumentation(documentation);
  }

  @Test
  public void descendant_of_a_relationship_is_a_relationship() {
    assertTrue(newRelation instanceof ZentaRelation);
  }

  @Test
  public void relationship_can_be_created_only_between_two_elements() {
    assertThrows(() -> model.createElement(rootFolder, basicRelation))
        .assertException(ReportedError.class);
  }

  @Test
  public void relationship_source_can_be_obtained() {
    assertEquals(sourceElement, newRelation.getSource());
  }

  @Test
  public void relationship_target_can_be_obtained() {
    assertEquals(targetElement, newRelation.getTarget());
  }

}
