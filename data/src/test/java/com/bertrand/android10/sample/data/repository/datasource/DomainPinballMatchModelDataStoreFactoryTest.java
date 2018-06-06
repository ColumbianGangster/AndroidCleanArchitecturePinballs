package com.bertrand.android10.sample.data.repository.datasource;

import com.bertrand.android10.sample.data.ApplicationTestCase;
import com.bertrand.android10.sample.data.cache.PinballMatchCache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DomainPinballMatchModelDataStoreFactoryTest extends ApplicationTestCase {

    private static final int FAKE_USER_ID = 11;

    private PinballDataStoreFactory pinballDataStoreFactory;

    @Mock
    private PinballMatchCache mockPinballMatchCache;

    @Before
    public void setUp() {
        pinballDataStoreFactory = new PinballDataStoreFactory(RuntimeEnvironment.application, mockPinballMatchCache);
    }

    @Test
    public void testCreateDiskDataStore() {
        given(mockPinballMatchCache.isCached(FAKE_USER_ID)).willReturn(true);
        given(mockPinballMatchCache.isExpired()).willReturn(false);

        UserDataStore userDataStore = pinballDataStoreFactory.create(FAKE_USER_ID);

        assertThat(userDataStore, is(notNullValue()));
        assertThat(userDataStore, is(instanceOf(DiskUserDataStore.class)));

        verify(mockPinballMatchCache).isCached(FAKE_USER_ID);
        verify(mockPinballMatchCache).isExpired();
    }

    @Test
    public void testCreateCloudDataStore() {
        given(mockPinballMatchCache.isExpired()).willReturn(true);
        given(mockPinballMatchCache.isCached(FAKE_USER_ID)).willReturn(false);

        UserDataStore userDataStore = pinballDataStoreFactory.create(FAKE_USER_ID);

        assertThat(userDataStore, is(notNullValue()));
        assertThat(userDataStore, is(instanceOf(CloudUserDataStore.class)));

        verify(mockPinballMatchCache).isExpired();
    }
}
