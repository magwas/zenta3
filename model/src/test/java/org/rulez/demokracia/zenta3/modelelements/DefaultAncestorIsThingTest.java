package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DefaultAncestorIsThingTest extends ZentaElementTest {

  @Test
  public void when_no_ancestor_found_then_thing_becomes_the_ancestor() {
    final ZentaElementImpl impl = (ZentaElementImpl) element;
    impl.node.setAttribute("ancestor", "nosuchclass");
    assertEquals("thing", impl.getAncestor().getId());
  }

}
