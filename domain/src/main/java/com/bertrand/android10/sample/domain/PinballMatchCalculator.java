package com.bertrand.android10.sample.domain;

public class PinballMatchCalculator {

    public int calculate(String pinballMatch) {
        if(pinballMatch == null || pinballMatch.trim().length() <= 0) {
            return -1;
        } else {
            return algorithm(pinballMatch);
        }
    }

    private int algorithm(String pinballMatch) {
        return 0;
    }
}
