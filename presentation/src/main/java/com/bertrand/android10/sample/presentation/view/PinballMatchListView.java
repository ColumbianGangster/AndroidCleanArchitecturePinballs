
package com.bertrand.android10.sample.presentation.view;

import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;

import java.util.Collection;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link UiPinballMatchModel}.
 */
public interface PinballMatchListView {
  void showPinballMatches(Collection<UiPinballMatchModel> pinballMatchModelCollection);

  boolean isReady();
}
