
package com.bertrand.android10.sample.domain;

/**
 * Class that represents a DomainPinballMatchModel in the domain layer.
 */
public class DomainPinballMatchModel {

  public DomainPinballMatchModel() {
  }

  private String pinballMatch;
  private int pinballMatchPointsTotal;

  public String getPinballMatch() {
    return pinballMatch;
  }

  public void setPinballMatch(String pinballMatch) {
    this.pinballMatch = pinballMatch;
  }

  public int getPinballMatchPointsTotal() {
    return pinballMatchPointsTotal;
  }

  public void setPinballMatchPointsTotal(int pinballMatchPointsTotal) {
    this.pinballMatchPointsTotal = pinballMatchPointsTotal;
  }
}
