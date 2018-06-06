package com.bertrand.android10.sample.data.repository;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.data.entity.mapper.PinballMatchEntityDataMapper;
import com.bertrand.android10.sample.data.repository.datasource.PinballDataStoreFactory;
import com.bertrand.android10.sample.data.repository.datasource.UserDataStore;
import com.bertrand.android10.sample.domain.DomainPinballMatchModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class DomainPinballMatchModelDataRepositoryTest {

    private PinballMatchDataRepository userDataRepository;

    @Mock
    private PinballDataStoreFactory mockPinballDataStoreFactory;
    @Mock
    private PinballMatchEntityDataMapper mockPinballMatchEntityDataMapper;
    @Mock
    private UserDataStore mockUserDataStore;
    @Mock
    private PinballMatchEntity mockPinballMatchEntity;
    @Mock
    private DomainPinballMatchModel mockDomainPinballMatchModel;

    @Before
    public void setUp() {
        userDataRepository = new PinballMatchDataRepository(mockPinballDataStoreFactory, mockPinballMatchEntityDataMapper);
        given(mockPinballDataStoreFactory.create(anyInt())).willReturn(mockUserDataStore);
        given(mockPinballDataStoreFactory.createCloudDataStore()).willReturn(mockUserDataStore);
    }

//  @Test
//  public void testGetUsersHappyCase() {
//    List<PinballMatchEntity> usersList = new ArrayList<>();
//    usersList.add(new PinballMatchEntity());
//    given(mockUserDataStore.userEntityList()).willReturn(Observable.just(usersList));
//
//    userDataRepository.getPinballMatches();
//
//    verify(mockUserDataStore).userEntityList();
//  }

//  @Test
//  public void testGetUserHappyCase() {
//    PinballMatchEntity userEntity = new PinballMatchEntity();
//    given(mockUserDataStore.userEntityDetails(FAKE_USER_ID)).willReturn(Observable.just(userEntity));
//    userDataRepository.createPinballMatch(FAKE_USER_ID);
//
//    verify(mockPinballDataStoreFactory).create(FAKE_USER_ID);
//    verify(mockUserDataStore).userEntityDetails(FAKE_USER_ID);
//  }
}
