
package com.bertrand.android10.sample.test.presenter;

import android.content.Context;

import com.bertrand.android10.sample.domain.interactor.GetPinballMatchList;
import com.bertrand.android10.sample.presentation.mapper.UserModelDataMapper;
import com.bertrand.android10.sample.presentation.presenter.PinballListPresenter;
import com.bertrand.android10.sample.presentation.view.PinballMatchListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DomainPinballMatchModelListPresenterTest {

  private PinballListPresenter userListPresenter;

  @Mock private Context mockContext;
  @Mock private PinballMatchListView mockUserListView;
  @Mock private GetPinballMatchList mockGetPinballMatchList;
  @Mock private UserModelDataMapper mockUserModelDataMapper;

  @Before
  public void setUp() {
    userListPresenter = new PinballListPresenter(mockGetPinballMatchList, mockUserModelDataMapper);
    userListPresenter.setView(mockUserListView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testUserListPresenterInitialize() {
    userListPresenter.initialize();
    verify(mockGetPinballMatchList).execute(any(DisposableObserver.class));
  }
}
