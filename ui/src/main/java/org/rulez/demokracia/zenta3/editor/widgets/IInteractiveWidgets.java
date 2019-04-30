package org.rulez.demokracia.zenta3.editor.widgets;

import java.io.File;

import com.google.inject.ImplementedBy;

@ImplementedBy(InteractiveWidgets.class)
public interface IInteractiveWidgets {

  File chooseFileToLoad(String title);

  File chooseFileToSave(String title);

}
