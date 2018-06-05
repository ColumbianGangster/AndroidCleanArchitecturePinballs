
package com.bertrand.android10.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.interactor.DefaultObserver;
import com.bertrand.android10.sample.domain.interactor.GetPinballMatchList;
import com.bertrand.android10.sample.presentation.internal.di.PerActivity;
import com.bertrand.android10.sample.presentation.mapper.UserModelDataMapper;
import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;
import com.bertrand.android10.sample.presentation.view.PinballMatchListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PinballListPresenter implements Presenter {

  private PinballMatchListView view;

  private final GetPinballMatchList getPinballMatchListUseCase;
  private final UserModelDataMapper userModelDataMapper;

  @Inject
  public PinballListPresenter(GetPinballMatchList getUserListPinballMatchCase,
                              UserModelDataMapper userModelDataMapper) {
    this.getPinballMatchListUseCase = getUserListPinballMatchCase;
    this.userModelDataMapper = userModelDataMapper;
  }

  public void setView(@NonNull PinballMatchListView view) {
    this.view = view;
  }

  @Override public void resume() {}

  @Override public void pause() {}

  @Override public void destroy() {
    this.getPinballMatchListUseCase.dispose();
    this.view = null;
  }

  public void initialize() {
    this.loadData();
  }

  private void loadData() {
//    this.hideViewRetry();
//    this.showViewLoading();
    this.getPinballMatches();
  }

//  public void onUserClicked(UiPinballMatchModel pinballMatchModel) {
//    this.view.viewUser(pinballMatchModel);
//  }
//
//  private void showViewLoading() {
//    this.view.showLoading();
//  }
//
//  private void hideViewLoading() {
//    this.view.hideLoading();
//  }
//
//  private void showViewRetry() {
//    this.view.showRetry();
//  }
//
//  private void hideViewRetry() {
//    this.view.hideRetry();
//  }
//
//  private void showErrorMessage(ErrorBundle errorBundle) {
//    String errorMessage = ErrorMessageFactory.create(this.view.context(),
//        errorBundle.getException());
//    this.view.showError(errorMessage);
//  }

  private void showPinballMatches(Collection<DomainPinballMatchModel> usersCollection) {
    final Collection<UiPinballMatchModel> pinballMatchModelsCollection =
        this.userModelDataMapper.transform(usersCollection);
    if(view != null && view.isReady()) {
        this.view.showPinballMatches(pinballMatchModelsCollection);
    }
  }

  private void getPinballMatches() {
    this.getPinballMatchListUseCase.execute(new PinballMatchListObserver());
  }

  private final class PinballMatchListObserver extends DefaultObserver<List<DomainPinballMatchModel>> {

    @Override public void onComplete() {
//      PinballListPresenter.this.hideViewLoading();
    }

    @Override public void onError(Throwable e) {
//      PinballListPresenter.this.hideViewLoading();
//      PinballListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//      PinballListPresenter.this.showViewRetry();
    }

    @Override public void onNext(List<DomainPinballMatchModel> domainPinballMatchModels) {
      PinballListPresenter.this.showPinballMatches(domainPinballMatchModels);
    }
  }
}
