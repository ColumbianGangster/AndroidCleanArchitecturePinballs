package com.bertrand.android10.sample.domain.repository;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;

import java.util.List;

import io.reactivex.Observable;

public interface PinballMatchRepository {
  Observable<List<DomainPinballMatchModel>> getPinballMatches();
  Observable<DomainPinballMatchModel> createPinballMatch(String pinballMatch);
}
