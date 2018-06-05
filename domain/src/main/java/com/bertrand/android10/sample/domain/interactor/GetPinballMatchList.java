package com.bertrand.android10.sample.domain.interactor;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link DomainPinballMatchModel}.
 */
public class GetPinballMatchList extends UseCase<List<DomainPinballMatchModel>> {

    private final PinballMatchRepository pinballMatchRepository;

    @Inject
    GetPinballMatchList(PinballMatchRepository pinballMatchRepository, ThreadExecutor threadExecutor,
                        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.pinballMatchRepository = pinballMatchRepository;
    }

    @Override
    Observable<List<DomainPinballMatchModel>> buildUseCaseObservable() {
        return this.pinballMatchRepository.getPinballMatches();
    }
}
