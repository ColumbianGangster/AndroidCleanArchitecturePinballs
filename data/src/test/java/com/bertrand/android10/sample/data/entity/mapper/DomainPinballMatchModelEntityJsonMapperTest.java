package com.bertrand.android10.sample.data.entity.mapper;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DomainPinballMatchModelEntityJsonMapperTest {

    private static final String JSON_RESPONSE_USER_DETAILS = "{\n"
            + "    \"id\": 1,\n"
            + "    \"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n"
            + "    \"full_name\": \"Simon Hill\",\n"
            + "    \"description\": \"Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.\",\n"
            + "    \"followers\": 7484,\n"
            + "    \"email\": \"jcooper@babbleset.edu\"\n"
            + "}";

    private static final String JSON_RESPONSE_USER_COLLECTION = "[{\n"
            + "    \"id\": 1,\n"
            + "    \"full_name\": \"Simon Hill\",\n"
            + "    \"followers\": 7484\n"
            + "}, {\n"
            + "    \"id\": 12,\n"
            + "    \"full_name\": \"Pedro Garcia\",\n"
            + "    \"followers\": 1381\n"
            + "}]";

    private PinballMatchEntityJsonMapper pinballMatchEntityJsonMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        pinballMatchEntityJsonMapper = new PinballMatchEntityJsonMapper();
    }

    @Test
    public void testTransformUserEntityHappyCase() {
        PinballMatchEntity pinballMatchEntity = pinballMatchEntityJsonMapper.transformUserEntity(JSON_RESPONSE_USER_DETAILS);

        assertThat(pinballMatchEntity.getUserId(), is(1));
        assertThat(pinballMatchEntity.getFullname(), is(equalTo("Simon Hill")));
        assertThat(pinballMatchEntity.getEmail(), is(equalTo("jcooper@babbleset.edu")));
    }

    @Test
    public void testTransformUserEntityCollectionHappyCase() {
        Collection<PinballMatchEntity> pinballMatchEntityCollection =
                pinballMatchEntityJsonMapper.transformUserEntityCollection(
                        JSON_RESPONSE_USER_COLLECTION);

        assertThat(((PinballMatchEntity) pinballMatchEntityCollection.toArray()[0]).getUserId(), is(1));
        assertThat(((PinballMatchEntity) pinballMatchEntityCollection.toArray()[1]).getUserId(), is(12));
        assertThat(pinballMatchEntityCollection.size(), is(2));
    }

    @Test
    public void testTransformUserEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        pinballMatchEntityJsonMapper.transformUserEntity("ironman");
    }

    @Test
    public void testTransformUserEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        pinballMatchEntityJsonMapper.transformUserEntityCollection("Tony Stark");
    }
}
