package com.bertrand.android10.sample.data.entity.mapper;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.domain.DomainPinballMatchModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DomainPinballMatchModelEntityDataMapperTest {

    private static final String FAKE_PINBALL_MATCH = "FAKE_PINBALL_MATCH";

    private PinballMatchEntityDataMapper pinballMatchEntityDataMapper;

    @Before
    public void setUp() throws Exception {
        pinballMatchEntityDataMapper = new PinballMatchEntityDataMapper();
    }

    @Test
    public void testTransformUserEntity() {
        PinballMatchEntity pinballMatchEntity = createFakeUserEntity();
        DomainPinballMatchModel domainPinballMatchModel = pinballMatchEntityDataMapper.transform(pinballMatchEntity);
        assertThat(domainPinballMatchModel, is(instanceOf(DomainPinballMatchModel.class)));
        assertThat(domainPinballMatchModel.getPinballMatch(), is(FAKE_PINBALL_MATCH));
    }

    @Test
    public void testTransformUserEntityCollection() {
        PinballMatchEntity mockPinballMatchEntityOne = mock(PinballMatchEntity.class);
        PinballMatchEntity mockPinballMatchEntityTwo = mock(PinballMatchEntity.class);

        List<PinballMatchEntity> pinballMatchEntityList = new ArrayList<PinballMatchEntity>(5);
        pinballMatchEntityList.add(mockPinballMatchEntityOne);
        pinballMatchEntityList.add(mockPinballMatchEntityTwo);

        Collection<DomainPinballMatchModel> domainPinballMatchModelCollection = pinballMatchEntityDataMapper.transform(pinballMatchEntityList);

        assertThat(domainPinballMatchModelCollection.toArray()[0], is(instanceOf(DomainPinballMatchModel.class)));
        assertThat(domainPinballMatchModelCollection.toArray()[1], is(instanceOf(DomainPinballMatchModel.class)));
        assertThat(domainPinballMatchModelCollection.size(), is(2));
    }

    private PinballMatchEntity createFakeUserEntity() {
        PinballMatchEntity pinballMatchEntity = new PinballMatchEntity();
        pinballMatchEntity.setFullname(FAKE_PINBALL_MATCH);
        return pinballMatchEntity;
    }
}
