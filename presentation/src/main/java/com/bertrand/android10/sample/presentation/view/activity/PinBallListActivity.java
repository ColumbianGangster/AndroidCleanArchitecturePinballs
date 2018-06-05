
package com.bertrand.android10.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.bertrand.android10.sample.presentation.R;
import com.bertrand.android10.sample.presentation.internal.di.HasComponent;
import com.bertrand.android10.sample.presentation.internal.di.components.DaggerPinBallListComponent;
import com.bertrand.android10.sample.presentation.internal.di.components.PinBallListComponent;
import com.bertrand.android10.sample.presentation.view.fragment.PinballListFragment;

public class PinBallListActivity extends BaseActivity implements HasComponent<PinBallListComponent> {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PinBallListActivity.class);
    }

    private PinBallListComponent pinBallListComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinball_list);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new PinballListFragment());
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> navigateToPinBallList());
    }

    public Context context() {
        return this;
    }

    void navigateToPinBallList() {
        this.navigator.navigateToNewPinBall(this);
    }

    private void initializeInjector() {
        this.pinBallListComponent = DaggerPinBallListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public PinBallListComponent getComponent() {
        return pinBallListComponent;
    }
}
