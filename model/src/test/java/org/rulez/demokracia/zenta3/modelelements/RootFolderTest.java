package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.Folder;

public class RootFolderTest extends FolderTest {

  private Folder rootFolder;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    rootFolder = model.getRootFolder();
    element = rootFolder;
    name = "Model";
    documentation = "Root folder";
    rootFolder.setDocumentation(documentation);
  }

  @Test
  public void the_thing_is_initially_in_the_root_folder() {
    assertTrue(rootFolder.getElements().contains(thing));
  }

  @Test
  public void the_basic_relation_is_initially_in_the_root_folder() {
    assertTrue(rootFolder.getElements().contains(basicRelation));
  }

  @Test
  public void the_isA_relation_is_initially_in_the_root_folder() {
    assertTrue(rootFolder.getElements().contains(basicRelation));
  }

}
