
package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.cache.UserCache;
import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
class DiskUserDataStore implements UserDataStore {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  DiskUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public Observable<List<PinballMatchEntity>> userEntityList() {
    //TODO: implement simple cache for storing/retrieving collections of getPinballMatches.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<PinballMatchEntity> userEntityDetails(final int userId) {
     return this.userCache.get(userId);
  }
}
