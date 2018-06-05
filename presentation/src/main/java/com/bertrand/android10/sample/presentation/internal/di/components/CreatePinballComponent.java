package com.bertrand.android10.sample.presentation.internal.di.components;

import com.bertrand.android10.sample.presentation.internal.di.PerActivity;
import com.bertrand.android10.sample.presentation.internal.di.modules.ActivityModule;
import com.bertrand.android10.sample.presentation.view.fragment.CreatePinBallMatchFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class
//        UserModule.class
})
public interface CreatePinballComponent extends ActivityComponent {
    void inject(CreatePinBallMatchFragment fragment);
}
