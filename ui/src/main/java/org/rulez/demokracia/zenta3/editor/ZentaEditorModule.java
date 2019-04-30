package org.rulez.demokracia.zenta3.editor;

import org.eclipse.gef.common.adapt.AdapterKey;
import org.eclipse.gef.mvc.fx.MvcFxModule;
import org.rulez.demokracia.zenta3.editor.model.EditorModelFactory;
import org.rulez.demokracia.zenta3.editor.parts.ZentaPartsFactory;
import org.rulez.demokracia.zenta3.editor.widgets.IInteractiveWidgets;
import org.rulez.demokracia.zenta3.editor.widgets.InteractiveWidgets;

import com.google.inject.multibindings.MapBinder;

public class ZentaEditorModule extends MvcFxModule {

  @Override
  protected void bindIContentPartFactoryAsContentViewerAdapter(
      final MapBinder<AdapterKey<?>, Object> adapterMapBinder
  ) {
    adapterMapBinder.addBinding(AdapterKey.defaultRole())
        .to(ZentaPartsFactory.class);
  }

  @Override
  protected void configure() {
    super.configure();
    bind(IInteractiveWidgets.class).to(InteractiveWidgets.class);
    requestStaticInjection(EditorModelFactory.class);
  }

}
