package org.rulez.demokracia.zenta3.model;

import java.io.Serializable;
import java.nio.file.Path;

import org.rulez.demokracia.zenta3.ZentaModelImpl;

public interface ZentaModel extends ProvidesElements, Serializable {

  static ZentaModel create() {
    final ZentaModel zentaModel = new ZentaModelImpl();
    zentaModel.getElementById(ZentaElement.THING);
    zentaModel.getElementById(ZentaElement.BASIC_RELATION);
    return zentaModel;
  }

  void saveModel(Path modelFile);

  static ZentaModel loadFile(final Path modelFile) {
    return new ZentaModelImpl(modelFile);
  }

}
