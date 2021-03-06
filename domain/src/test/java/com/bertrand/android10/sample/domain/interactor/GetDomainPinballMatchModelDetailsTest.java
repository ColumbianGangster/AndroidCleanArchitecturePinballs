
package com.bertrand.android10.sample.domain.interactor;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetDomainPinballMatchModelDetailsTest {

    private static final String PINBALL_MATCH = "PINBALL_MATCH";

    private CreatePinballMatchUseCase createPinballMatchUseCase;

    @Mock
    private PinballMatchRepository mockPinballMatchRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        createPinballMatchUseCase = new CreatePinballMatchUseCase(mockPinballMatchRepository, mockThreadExecutor,
                mockPostExecutionThread);
    }

    @Test
    public void testGetUserDetailsUseCaseObservableHappyCase() {
        DomainPinballMatchModel mockPinballMatchModel = mock(DomainPinballMatchModel.class);
        when(mockPinballMatchModel.getPinballMatch()).thenReturn(PINBALL_MATCH);
        when(mockPinballMatchRepository.createPinballMatch(PINBALL_MATCH)).thenReturn(Observable.just(mockPinballMatchModel));
        createPinballMatchUseCase.setPinballMatch(PINBALL_MATCH);
        createPinballMatchUseCase.buildUseCaseObservable();

        verify(mockPinballMatchRepository).createPinballMatch(PINBALL_MATCH);
        verifyNoMoreInteractions(mockPinballMatchRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }

    @Test
    public void testShouldFailWhenNoOrEmptyParameters() {
        expectedException.expect(IllegalStateException.class);
        createPinballMatchUseCase.buildUseCaseObservable();
    }
}
