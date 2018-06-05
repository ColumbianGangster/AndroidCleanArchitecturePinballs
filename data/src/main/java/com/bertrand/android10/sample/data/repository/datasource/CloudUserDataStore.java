
package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.cache.UserCache;
import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.data.net.RestApi;

import io.reactivex.Observable;
import java.util.List;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
class CloudUserDataStore implements UserDataStore {

  private final RestApi restApi;
  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param restApi The {@link RestApi} implementation to use.
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudUserDataStore(RestApi restApi, UserCache userCache) {
    this.restApi = restApi;
    this.userCache = userCache;
  }

  @Override public Observable<List<PinballMatchEntity>> userEntityList() {
    return this.restApi.userEntityList();
  }

  @Override public Observable<PinballMatchEntity> userEntityDetails(final int userId) {
    return this.restApi.userEntityById(userId).doOnNext(CloudUserDataStore.this.userCache::put);
  }
}
