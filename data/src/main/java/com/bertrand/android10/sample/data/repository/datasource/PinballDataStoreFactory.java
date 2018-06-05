
package com.bertrand.android10.sample.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bertrand.android10.sample.data.cache.PinballMatchCache;
import com.bertrand.android10.sample.data.entity.mapper.PinballMatchEntityJsonMapper;
import com.bertrand.android10.sample.data.net.RestApi;
import com.bertrand.android10.sample.data.net.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link UserDataStore}.
 */
@Singleton
public class PinballDataStoreFactory {

  private final Context context;
  private final PinballMatchCache pinballMatchCache;

  @Inject
  PinballDataStoreFactory(@NonNull Context context, @NonNull PinballMatchCache pinballMatchCache) {
    this.context = context.getApplicationContext();
    this.pinballMatchCache = pinballMatchCache;
  }

  /**
   * Create {@link UserDataStore} from a user id.
   */
  public UserDataStore create(int userId) {
    UserDataStore userDataStore;

    if (!this.pinballMatchCache.isExpired() && this.pinballMatchCache.isCached(userId)) {
      userDataStore = new DiskUserDataStore(this.pinballMatchCache);
    } else {
      userDataStore = createCloudDataStore();
    }

    return userDataStore;
  }

  /**
   * Create {@link UserDataStore} to retrieve data from the Cloud.
   */
  public UserDataStore createCloudDataStore() {
    final PinballMatchEntityJsonMapper pinballMatchEntityJsonMapper = new PinballMatchEntityJsonMapper();
    final RestApi restApi = new RestApiImpl(this.context, pinballMatchEntityJsonMapper);

    return new CloudUserDataStore(restApi, this.pinballMatchCache);
  }
}
