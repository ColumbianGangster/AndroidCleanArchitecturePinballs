package com.bertrand.android10.sample.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.bertrand.android10.sample.presentation.AndroidApplication;
import com.bertrand.android10.sample.presentation.internal.di.components.ApplicationComponent;
import com.bertrand.android10.sample.presentation.internal.di.modules.ActivityModule;
import com.bertrand.android10.sample.presentation.navigation.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

  @Inject
  Navigator navigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }

  protected void addFragment(int containerViewId, Fragment fragment) {
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.add(containerViewId, fragment);
      fragmentTransaction.commit();
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
