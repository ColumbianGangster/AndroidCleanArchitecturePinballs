
package com.bertrand.android10.sample.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.bertrand.android10.sample.presentation.view.activity.CreatePinballGameActivity;
import com.bertrand.android10.sample.presentation.view.activity.PinBallListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {

    }

    public void navigateToPinBallList(Context context) {
        if (context != null) {
            Intent intentToLaunch = PinBallListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToNewPinBall(Context context) {
        if (context != null) {
            Intent intentToLaunch = CreatePinballGameActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}


