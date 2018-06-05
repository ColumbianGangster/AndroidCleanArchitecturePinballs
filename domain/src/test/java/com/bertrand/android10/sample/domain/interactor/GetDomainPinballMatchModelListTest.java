
package com.bertrand.android10.sample.domain.interactor;

import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetDomainPinballMatchModelListTest {

  private GetPinballMatchList getPinballMatchList;

  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;
  @Mock private PinballMatchRepository mockPinballMatchRepository;

  @Before
  public void setUp() {
    getPinballMatchList = new GetPinballMatchList(mockPinballMatchRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetUserListUseCaseObservableHappyCase() {
    getPinballMatchList.buildUseCaseObservable();

    verify(mockPinballMatchRepository).getPinballMatches();
    verifyNoMoreInteractions(mockPinballMatchRepository);
    verifyZeroInteractions(mockThreadExecutor);
    verifyZeroInteractions(mockPostExecutionThread);
  }
}
