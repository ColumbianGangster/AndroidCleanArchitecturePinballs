
package com.bertrand.android10.sample.data.cache.serializer;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SerializerTest {

  private static final String JSON_RESPONSE = "{\n"
      + "    \"id\": 1,\n"
      + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
      + "    \"full_name\": \"Simon Hill\",\n"
      + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
      + "    \"followers\": 7484,\n"
      + "    \"email\": \"jcooper@babbleset.edu\"\n"
      + "}";

  private Serializer serializer;

  @Before
  public void setUp() {
    serializer = new Serializer();
  }

  @Test
  public void testSerializeHappyCase() {
    final PinballMatchEntity pinballMatchEntityOne = serializer.deserialize(JSON_RESPONSE, PinballMatchEntity.class);
    final String jsonString = serializer.serialize(pinballMatchEntityOne, PinballMatchEntity.class);
    final PinballMatchEntity pinballMatchEntityTwo = serializer.deserialize(jsonString, PinballMatchEntity.class);

    assertThat(pinballMatchEntityOne.getUserId(), is(pinballMatchEntityTwo.getUserId()));
    assertThat(pinballMatchEntityOne.getFullname(), is(equalTo(pinballMatchEntityTwo.getFullname())));
    assertThat(pinballMatchEntityOne.getFollowers(), is(pinballMatchEntityTwo.getFollowers()));
  }

  @Test
  public void testDesearializeHappyCase() {
    final PinballMatchEntity pinballMatchEntity = serializer.deserialize(JSON_RESPONSE, PinballMatchEntity.class);

    assertThat(pinballMatchEntity.getUserId(), is(1));
    assertThat(pinballMatchEntity.getFullname(), is("Simon Hill"));
    assertThat(pinballMatchEntity.getFollowers(), is(7484));
  }
}
