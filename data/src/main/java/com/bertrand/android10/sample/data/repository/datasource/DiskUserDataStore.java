
package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.cache.PinballMatchCache;
import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * {@link UserDataStore} implementation based on file system data store.
 */
class DiskUserDataStore implements UserDataStore {

  private final PinballMatchCache pinballMatchCache;

  /**
   * Construct a {@link UserDataStore} based file system data store.
   *
   * @param pinballMatchCache A {@link PinballMatchCache} to cache data retrieved from the api.
   */
  DiskUserDataStore(PinballMatchCache pinballMatchCache) {
    this.pinballMatchCache = pinballMatchCache;
  }

  @Override public Observable<List<PinballMatchEntity>> userEntityList() {
    //TODO: implement simple cache for storing/retrieving collections of getPinballMatches.
    throw new UnsupportedOperationException("Operation is not available!!!");
  }

  @Override public Observable<PinballMatchEntity> userEntityDetails(final int userId) {
     return this.pinballMatchCache.get(userId);
  }
}
