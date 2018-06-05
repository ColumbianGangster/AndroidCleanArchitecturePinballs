package com.bertrand.android10.sample.domain.interactor;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.PinballMatchCalculator;
import com.bertrand.android10.sample.domain.executor.PostExecutionThread;
import com.bertrand.android10.sample.domain.executor.ThreadExecutor;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link DomainPinballMatchModel}.
 */
public class CreatePinballMatchUseCase extends UseCase<DomainPinballMatchModel> {

  private final PinballMatchRepository pinballMatchRepository;

  private String pinballMatch;

  @Inject
  CreatePinballMatchUseCase(PinballMatchRepository pinballMatchRepository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.pinballMatchRepository = pinballMatchRepository;
  }

  public void setPinballMatch(String pinballMatch) {
      this.pinballMatch = pinballMatch;
  }

  @Override Observable<DomainPinballMatchModel> buildUseCaseObservable() {
    if(pinballMatch == null) {
      throw new IllegalStateException("pinballMatch must not be null");
    }
    return this.pinballMatchRepository.createPinballMatch(pinballMatch).flatMap(new Function<DomainPinballMatchModel, ObservableSource<DomainPinballMatchModel>>() {
        @Override
        public ObservableSource<DomainPinballMatchModel> apply(DomainPinballMatchModel domainPinballMatchModel) throws Exception {
            domainPinballMatchModel.setPinballMatchPointsTotal(determinePointsTotal(domainPinballMatchModel.getPinballMatch()));
            return Observable.just(domainPinballMatchModel);
        }

        private int determinePointsTotal(String pinballMatch) {
            PinballMatchCalculator pinballMatchCalculator = new PinballMatchCalculator();
            int pinballMatchPointTotal = pinballMatchCalculator.calculate(pinballMatch);
            return pinballMatchPointTotal;
        }
    });
  }
}
