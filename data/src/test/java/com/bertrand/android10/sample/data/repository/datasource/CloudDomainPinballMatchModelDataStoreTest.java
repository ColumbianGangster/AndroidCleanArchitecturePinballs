package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.cache.PinballMatchCache;
import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.data.net.RestApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CloudDomainPinballMatchModelDataStoreTest {

    private static final int FAKE_USER_ID = 765;

    private CloudUserDataStore cloudUserDataStore;

    @Mock
    private RestApi mockRestApi;
    @Mock
    private PinballMatchCache mockPinballMatchCache;

    @Before
    public void setUp() {
        cloudUserDataStore = new CloudUserDataStore(mockRestApi, mockPinballMatchCache);
    }

    @Test
    public void testGetUserEntityListFromApi() {
        cloudUserDataStore.userEntityList();
        verify(mockRestApi).userEntityList();
    }

    @Test
    public void testGetUserEntityDetailsFromApi() {
        PinballMatchEntity fakePinballMatchEntity = new PinballMatchEntity();
        Observable<PinballMatchEntity> fakeObservable = Observable.just(fakePinballMatchEntity);
        given(mockRestApi.userEntityById(FAKE_USER_ID)).willReturn(fakeObservable);

        cloudUserDataStore.userEntityDetails(FAKE_USER_ID);

        verify(mockRestApi).userEntityById(FAKE_USER_ID);
    }
}
