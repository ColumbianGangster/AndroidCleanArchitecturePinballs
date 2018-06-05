
package com.bertrand.android10.sample.presentation.internal.di.modules;

import android.content.Context;

import com.bertrand.android10.sample.data.cache.UserCache;
import com.bertrand.android10.sample.data.cache.UserCacheImpl;
import com.bertrand.android10.sample.data.executor.JobExecutor;
import com.bertrand.android10.sample.data.repository.PinballMatchDataRepository;
import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;
import com.bertrand.android10.sample.presentation.AndroidApplication;
import com.bertrand.android10.sample.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton
  PinballMatchRepository provideUserRepository(PinballMatchDataRepository userDataRepository) {
    return userDataRepository;
  }
}
