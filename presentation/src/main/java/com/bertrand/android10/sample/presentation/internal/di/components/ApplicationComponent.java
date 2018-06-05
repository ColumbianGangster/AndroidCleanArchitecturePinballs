
package com.bertrand.android10.sample.presentation.internal.di.components;

import android.content.Context;

import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;
import com.bertrand.android10.sample.presentation.internal.di.modules.ApplicationModule;
import com.bertrand.android10.sample.presentation.view.activity.BaseActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  PinballMatchRepository userRepository();
}
