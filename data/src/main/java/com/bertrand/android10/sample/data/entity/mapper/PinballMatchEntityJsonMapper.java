
package com.bertrand.android10.sample.data.entity.mapper;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

/**
 * Class used to transform from Strings representing json to valid objects.
 */
public class PinballMatchEntityJsonMapper {

  private final Gson gson;

  @Inject
  public PinballMatchEntityJsonMapper() {
    this.gson = new Gson();
  }

  /**
   * Transform from valid json string to {@link PinballMatchEntity}.
   *
   * @param userJsonResponse A json representing a user profile.
   * @return {@link PinballMatchEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public PinballMatchEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
    final Type userEntityType = new TypeToken<PinballMatchEntity>() {}.getType();
    return this.gson.fromJson(userJsonResponse, userEntityType);
  }

  /**
   * Transform from valid json string to List of {@link PinballMatchEntity}.
   *
   * @param userListJsonResponse A json representing a collection of getPinballMatches.
   * @return List of {@link PinballMatchEntity}.
   * @throws com.google.gson.JsonSyntaxException if the json string is not a valid json structure.
   */
  public List<PinballMatchEntity> transformUserEntityCollection(String userListJsonResponse)
      throws JsonSyntaxException {
    final Type listOfUserEntityType = new TypeToken<List<PinballMatchEntity>>() {}.getType();
    return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
  }
}
