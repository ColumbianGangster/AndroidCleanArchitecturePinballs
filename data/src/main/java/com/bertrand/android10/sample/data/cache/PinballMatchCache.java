
package com.bertrand.android10.sample.data.cache;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */
public interface PinballMatchCache {
  /**
   * Gets an {@link Observable} which will emit a {@link PinballMatchEntity}.
   *
   * @param userId The user id to retrieve data.
   */
  Observable<PinballMatchEntity> get(final int userId);

  /**
   * Puts and element into the cache.
   *
   * @param pinballMatchEntity Element to insert in the cache.
   */
  void put(PinballMatchEntity pinballMatchEntity);

  /**
   * Checks if an element (DomainPinballMatchModel) exists in the cache.
   *
   * @param userId The id used to look for inside the cache.
   * @return true if the element is cached, otherwise false.
   */
  boolean isCached(final int userId);

  /**
   * Checks if the cache is expired.
   *
   * @return true, the cache is expired, otherwise false.
   */
  boolean isExpired();

  /**
   * Evict all elements of the cache.
   */
  void evictAll();
}
