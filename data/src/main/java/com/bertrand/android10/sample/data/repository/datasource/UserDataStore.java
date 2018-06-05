
package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface UserDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link PinballMatchEntity}.
   */
  Observable<List<PinballMatchEntity>> userEntityList();

  /**
   * Get an {@link Observable} which will emit a {@link PinballMatchEntity} by its id.
   *
   * @param userId The id to retrieve user data.
   */
  Observable<PinballMatchEntity> userEntityDetails(final int userId);
}
