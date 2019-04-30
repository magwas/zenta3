package org.rulez.demokracia.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.rulez.demokracia.testhelpers.ThrowableTester;
import org.rulez.magwas.errors.ReportedError;

public class ThrowableTesterTest extends ThrowableTester {

  @Test
  public void fails_if_no_exception_is_thrown() {
    try {
      assertThrows(() -> nonThrowing());
    } catch (final AssertionError e) {
      assertEquals("no exception thrown", e.getMessage());
    }
  }

  @Test
  public void unimplemented_functionality_can_be_checked() {
    assertEquals(
        "unimplemented",
        assertUnimplemented(() -> unimplemented()).getException().getMessage()
    );
  }

  @Test
  public void ReportedError_can_be_instantiated_with_a_message() {
    final String expected = "message";
    final ReportedError error = new ReportedError(expected);
    assertEquals(expected, error.getMessage());
  }

  private void nonThrowing() {
  }

  public void unimplemented() {
    throw new UnsupportedOperationException("unimplemented");
  }
}
