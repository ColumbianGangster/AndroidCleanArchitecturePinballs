
package com.bertrand.android10.sample.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.data.entity.mapper.PinballMatchEntityJsonMapper;
import com.bertrand.android10.sample.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */
public class RestApiImpl implements RestApi {

  private final Context context;
  private final PinballMatchEntityJsonMapper pinballMatchEntityJsonMapper;

  /**
   * Constructor of the class
   *
   * @param context {@link android.content.Context}.
   * @param pinballMatchEntityJsonMapper {@link PinballMatchEntityJsonMapper}.
   */
  public RestApiImpl(Context context, PinballMatchEntityJsonMapper pinballMatchEntityJsonMapper) {
    if (context == null || pinballMatchEntityJsonMapper == null) {
      throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
    }
    this.context = context.getApplicationContext();
    this.pinballMatchEntityJsonMapper = pinballMatchEntityJsonMapper;
  }

  @Override public Observable<List<PinballMatchEntity>> userEntityList() {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          String responseUserEntities = getUserEntitiesFromApi();
          if (responseUserEntities != null) {
            emitter.onNext(pinballMatchEntityJsonMapper.transformUserEntityCollection(
                responseUserEntities));
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  @Override public Observable<PinballMatchEntity> userEntityById(final int userId) {
    return Observable.create(emitter -> {
      if (isThereInternetConnection()) {
        try {
          String responseUserDetails = getUserDetailsFromApi(userId);
          if (responseUserDetails != null) {
            emitter.onNext(pinballMatchEntityJsonMapper.transformUserEntity(responseUserDetails));
            emitter.onComplete();
          } else {
            emitter.onError(new NetworkConnectionException());
          }
        } catch (Exception e) {
          emitter.onError(new NetworkConnectionException(e.getCause()));
        }
      } else {
        emitter.onError(new NetworkConnectionException());
      }
    });
  }

  private String getUserEntitiesFromApi() throws MalformedURLException {
    return ApiConnection.createGET(API_URL_GET_USER_LIST).requestSyncCall();
  }

  private String getUserDetailsFromApi(int userId) throws MalformedURLException {
    String apiUrl = API_URL_GET_USER_DETAILS + userId + ".json";
    return ApiConnection.createGET(apiUrl).requestSyncCall();
  }

  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
