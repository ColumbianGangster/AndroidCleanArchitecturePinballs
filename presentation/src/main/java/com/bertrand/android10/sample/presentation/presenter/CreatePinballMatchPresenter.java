package com.bertrand.android10.sample.presentation.presenter;

import android.support.annotation.NonNull;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.interactor.CreatePinballMatch;
import com.bertrand.android10.sample.domain.interactor.DefaultObserver;
import com.bertrand.android10.sample.presentation.internal.di.PerActivity;
import com.bertrand.android10.sample.presentation.mapper.UserModelDataMapper;
import com.bertrand.android10.sample.presentation.view.CreatePinballView;

import javax.inject.Inject;

@PerActivity
public class CreatePinballMatchPresenter implements Presenter {
    private final CreatePinballMatch createPinballMatchUseCase;
    private final UserModelDataMapper userModelDataMapper;
    private CreatePinballView view;

    @Inject
    public CreatePinballMatchPresenter(CreatePinballMatch createPinballMatchUseCase,
                                UserModelDataMapper userModelDataMapper) {
        this.createPinballMatchUseCase = createPinballMatchUseCase;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull CreatePinballView view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.createPinballMatchUseCase.dispose();
        this.view = null;
    }

    public void createPinballMatch(String newPinballMatch) {
        this.createPinballMatchUseCase.setPinballMatch(newPinballMatch);
        this.createPinballMatchUseCase.execute(new CreatePinballMatchPresenter.CreatePinballMatchObserver());
    }

    private final class CreatePinballMatchObserver extends DefaultObserver<DomainPinballMatchModel> {

        @Override public void onError(Throwable e) {
//            CreatePinballMatchPresenter.this.hideViewLoading();
//            CreatePinballMatchPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//            CreatePinballMatchPresenter.this.showViewRetry();
        }

        @Override public void onNext(DomainPinballMatchModel domainPinballMatchModel) {
            CreatePinballMatchPresenter.this.onPinballMatchCreated(domainPinballMatchModel);
        }
    }

    private void onPinballMatchCreated(DomainPinballMatchModel domainPinballMatchModel) {
        if(view != null) {
            view.onPinballMatchCreated(domainPinballMatchModel);
        }
    }
}
