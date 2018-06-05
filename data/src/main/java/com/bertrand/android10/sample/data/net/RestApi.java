
package com.bertrand.android10.sample.data.net;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL =
      "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

  /** Api url for getting all getPinballMatches */
  String API_URL_GET_USER_LIST = API_BASE_URL + "getPinballMatches.json";
  /** Api url for getting a user profile: Remember to concatenate id + 'json' */
  String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link PinballMatchEntity}.
   */
  Observable<List<PinballMatchEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link PinballMatchEntity}.
   *
   * @param userId The user id used to get user data.
   */
  Observable<PinballMatchEntity> userEntityById(final int userId);
}
