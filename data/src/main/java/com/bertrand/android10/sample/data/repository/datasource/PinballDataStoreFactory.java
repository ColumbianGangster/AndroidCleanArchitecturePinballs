
package com.bertrand.android10.sample.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bertrand.android10.sample.data.cache.UserCache;
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
  private final UserCache userCache;

  @Inject
  PinballDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache) {
    this.context = context.getApplicationContext();
    this.userCache = userCache;
  }

  /**
   * Create {@link UserDataStore} from a user id.
   */
  public UserDataStore create(int userId) {
    UserDataStore userDataStore;

    if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
      userDataStore = new DiskUserDataStore(this.userCache);
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

    return new CloudUserDataStore(restApi, this.userCache);
  }
}
