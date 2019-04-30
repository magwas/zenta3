package org.rulez.demokracia.zenta3.modelelements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rulez.demokracia.zenta3.model.Folder;

public class FolderTest extends RelationshipTest {

  private Folder folder;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    folder = model.getRootFolder();
    element = model.createFolder(folder);
    ancestor = model.getElementById("folder");
    documentation = "folder";
    element.setDocumentation(documentation);
    name = "Folder";
    element.setName(name);
  }

  @Test
  public void a_folder_is_a_Folder() {
    assertTrue(folder instanceof Folder);
  }

  @Test
  public void a_folder_has_an_id() {
    assertFalse("".equals(folder.getId()));
  }

  @Test
  public void the_name_of_the_folder_can_be_set_and_get() {
    final String name = "A test element";
    folder.setName(name);
    assertEquals(name, folder.getName());
  }

  @Test
  public void a_folder_can_be_created_in_the_folder() {
    final Folder newFolder = model.createFolder(folder);
    assertTrue(folder.getElements().contains(newFolder));
  }

}
