package org.rulez.demokracia.zenta3.editor;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.gef.mvc.fx.domain.HistoricizingDomain;

import javafx.scene.control.Button;

public class DomainHandler {

  private final HistoricizingDomain domain;

  public DomainHandler() {
    domain = new HistoricizingDomain();
    domain.activate();

    final IOperationHistory operationHistory =
        OperationHistoryFactory.getOperationHistory();
    domain.setOperationHistory(operationHistory);
  }

  public void setButtons(final Button undoButton, final Button redoButton) {
    addButtonOperationHistoryViewer(undoButton, redoButton);
  }

  private void addButtonOperationHistoryViewer(
      final Button undoButton, final Button redoButton
  ) {
  }

}
