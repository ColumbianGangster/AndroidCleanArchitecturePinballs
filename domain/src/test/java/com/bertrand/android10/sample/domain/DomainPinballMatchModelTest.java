
package com.bertrand.android10.sample.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DomainPinballMatchModelTest {

    private static final String PINBALL_MATCH = "PINBALL_MATCH";

    private DomainPinballMatchModel domainPinballMatchModel;

    @Before
    public void setUp() {
        domainPinballMatchModel = new DomainPinballMatchModel();
        domainPinballMatchModel.setPinballMatch(PINBALL_MATCH);
    }

    @Test
    public void testUserConstructorHappyCase() {
        final String pinballMatch = domainPinballMatchModel.getPinballMatch();

        assertThat(pinballMatch).isEqualTo(PINBALL_MATCH);
    }
}
