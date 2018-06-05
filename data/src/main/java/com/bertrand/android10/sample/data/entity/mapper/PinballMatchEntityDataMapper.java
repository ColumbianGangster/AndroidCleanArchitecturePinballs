package com.bertrand.android10.sample.data.entity.mapper;

import com.bertrand.android10.sample.data.entity.PinballMatchEntity;
import com.bertrand.android10.sample.domain.DomainPinballMatchModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link PinballMatchEntity} (in the data layer) to {@link DomainPinballMatchModel} in the
 * domain layer.
 */
@Singleton
public class PinballMatchEntityDataMapper {

  @Inject
  PinballMatchEntityDataMapper() {}

  /**
   * Transform a {@link PinballMatchEntity} into an {@link DomainPinballMatchModel}.
   *
   * @param pinballMatchEntity Object to be transformed.
   * @return {@link DomainPinballMatchModel} if valid {@link PinballMatchEntity} otherwise null.
   */
  public DomainPinballMatchModel transform(PinballMatchEntity pinballMatchEntity) {
    DomainPinballMatchModel domainPinballMatchModel = null;
    if (pinballMatchEntity != null) {
      domainPinballMatchModel = new DomainPinballMatchModel();
      domainPinballMatchModel.setPinballMatch(pinballMatchEntity.getFullname());
      domainPinballMatchModel.setPinballMatchPointsTotal(pinballMatchEntity.getFollowers());
    }
    return domainPinballMatchModel;
  }

  /**
   * Transform a List of {@link PinballMatchEntity} into a Collection of {@link DomainPinballMatchModel}.
   *
   * @param pinballMatchEntityCollection Object Collection to be transformed.
   * @return {@link DomainPinballMatchModel} if valid {@link PinballMatchEntity} otherwise null.
   */
  public List<DomainPinballMatchModel> transform(Collection<PinballMatchEntity> pinballMatchEntityCollection) {
    final List<DomainPinballMatchModel> domainPinballMatchModelList = new ArrayList<>(20);
    for (PinballMatchEntity pinballMatchEntity : pinballMatchEntityCollection) {
      final DomainPinballMatchModel domainPinballMatchModel = transform(pinballMatchEntity);
      if (domainPinballMatchModel != null) {
        domainPinballMatchModelList.add(domainPinballMatchModel);
      }
    }
    return domainPinballMatchModelList;
  }
}
