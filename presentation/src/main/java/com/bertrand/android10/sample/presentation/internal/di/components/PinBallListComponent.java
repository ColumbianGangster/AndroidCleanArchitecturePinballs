
package com.bertrand.android10.sample.presentation.internal.di.components;

import com.bertrand.android10.sample.presentation.internal.di.PerActivity;
import com.bertrand.android10.sample.presentation.internal.di.modules.ActivityModule;
import com.bertrand.android10.sample.presentation.view.fragment.PinballListFragment;

import dagger.Component;

/**
 * A scope {@link com.bertrand.android10.sample.presentation.internal.di.PerActivity} component.
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class
//        UserModule.class
})
public interface PinBallListComponent extends ActivityComponent {
  void inject(PinballListFragment fragment);
}
