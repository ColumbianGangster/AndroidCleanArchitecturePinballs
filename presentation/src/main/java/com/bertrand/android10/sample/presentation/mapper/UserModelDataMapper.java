
package com.bertrand.android10.sample.presentation.mapper;

import com.bertrand.android10.sample.domain.DomainPinballMatchModel;
import com.bertrand.android10.sample.presentation.internal.di.PerActivity;
import com.bertrand.android10.sample.presentation.model.UiPinballMatchModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link DomainPinballMatchModel} (in the domain layer) to {@link UiPinballMatchModel} in the
 * presentation layer.
 */
@PerActivity
public class UserModelDataMapper {

    @Inject
    public UserModelDataMapper() {
    }

    /**
     * Transform a {@link DomainPinballMatchModel} into an {@link UiPinballMatchModel}.
     *
     * @param domainPinballMatchModel Object to be transformed.
     * @return {@link UiPinballMatchModel}.
     */
    public UiPinballMatchModel transform(DomainPinballMatchModel domainPinballMatchModel) {
        if (domainPinballMatchModel == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final UiPinballMatchModel pinballMatchModel = new UiPinballMatchModel();
        pinballMatchModel.setPinballMatch(domainPinballMatchModel.getPinballMatch());
        pinballMatchModel.setPinballMatchPointsTotal(domainPinballMatchModel.getPinballMatchPointsTotal());

        return pinballMatchModel;
    }

    /**
     * Transform a Collection of {@link DomainPinballMatchModel} into a Collection of {@link UiPinballMatchModel}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link UiPinballMatchModel}.
     */
    public Collection<UiPinballMatchModel> transform(Collection<DomainPinballMatchModel> usersCollection) {
        Collection<UiPinballMatchModel> pinballMatchModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            pinballMatchModelsCollection = new ArrayList<>();
            for (DomainPinballMatchModel domainPinballMatchModel : usersCollection) {
                pinballMatchModelsCollection.add(transform(domainPinballMatchModel));
            }
        } else {
            pinballMatchModelsCollection = Collections.emptyList();
        }

        return pinballMatchModelsCollection;
    }
}
