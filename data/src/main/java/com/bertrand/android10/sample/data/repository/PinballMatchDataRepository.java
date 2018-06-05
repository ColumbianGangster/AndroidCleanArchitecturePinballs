package com.bertrand.android10.sample.data.repository;

import com.bertrand.android10.sample.data.entity.mapper.PinballMatchEntityDataMapper;
import com.bertrand.android10.sample.data.repository.datasource.PinballDataStoreFactory;
import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.domain.repository.PinballMatchRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PinballMatchDataRepository implements PinballMatchRepository {

  private final PinballDataStoreFactory pinballDataStoreFactory;
  private final PinballMatchEntityDataMapper pinballMatchEntityDataMapper;

  /**
   * Constructs a {@link PinballMatchRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   * @param pinballMatchEntityDataMapper {@link PinballMatchEntityDataMapper}.
   */
  @Inject
  PinballMatchDataRepository(PinballDataStoreFactory dataStoreFactory,
                             PinballMatchEntityDataMapper pinballMatchEntityDataMapper) {
    this.pinballDataStoreFactory = dataStoreFactory;
    this.pinballMatchEntityDataMapper = pinballMatchEntityDataMapper;
  }

  @Override public Observable<List<DomainPinballMatchModel>> getPinballMatches() {
      List<DomainPinballMatchModel> mockList = new ArrayList<>();

      DomainPinballMatchModel mock1 = new DomainPinballMatchModel();
      mock1.setPinballMatch("X|X|X|X|X|X|X|X|X|X||XX");
      mock1.setPinballMatchPointsTotal(300);

      DomainPinballMatchModel mock2 = new DomainPinballMatchModel();
      mock2.setPinballMatch("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||");
      mock2.setPinballMatchPointsTotal(90);

      DomainPinballMatchModel mock3 = new DomainPinballMatchModel();
      mock3.setPinballMatch("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5");
      mock3.setPinballMatchPointsTotal(150);

      DomainPinballMatchModel mock4 = new DomainPinballMatchModel();
      mock4.setPinballMatch("X|7/|9-|X|-8|8/|-6|X|X|X||81");
      mock4.setPinballMatchPointsTotal(167);

      mockList.add(mock1);
      mockList.add(mock2);
      mockList.add(mock3);
      mockList.add(mock4);

      return Observable.just(mockList);
  }

  @Override
  public Observable<DomainPinballMatchModel> createPinballMatch(String pinballMatch) {
      if(pinballMatch == null || pinballMatch.trim().length() > 0) {

      }
      DomainPinballMatchModel mock = new DomainPinballMatchModel();
      mock.setPinballMatch("X|X|X|X|X|X|X|X|X|X||XX");
      mock.setPinballMatchPointsTotal(300);
      return Observable.just(mock);
//    final UserDataStore userDataStore = this.pinballDataStoreFactory.create(pinballMatch);
//    return userDataStore.userEntityDetails(pinballMatch).map(this.pinballMatchEntityDataMapper::transform);
  }
}
