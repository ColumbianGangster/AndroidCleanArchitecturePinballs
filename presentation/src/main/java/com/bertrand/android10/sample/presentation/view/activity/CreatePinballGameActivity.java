package com.bertrand.android10.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.internal.di.HasComponent;
import com.bertrand.android10.sample.presentation.internal.di.components.CreatePinballComponent;
import com.bertrand.android10.sample.presentation.internal.di.components.DaggerCreatePinballComponent;
import com.bertrand.android10.sample.presentation.view.fragment.CreatePinBallMatchFragment;

public class CreatePinballGameActivity extends BaseActivity implements HasComponent<CreatePinballComponent> {
    private CreatePinballComponent userComponent;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, CreatePinballGameActivity.class);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pinball_activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new CreatePinBallMatchFragment());
        }
    }

    private void initializeInjector() {
        this.userComponent = DaggerCreatePinballComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    public Context context() {
        return this;
    }

    @Override
    public CreatePinballComponent getComponent() {
        return userComponent;
    }
}
