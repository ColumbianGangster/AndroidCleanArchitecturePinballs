package com.bertrand.android10.sample.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PinballMatchCalculatorTest {

    // system under test
    private PinballMatchCalculator calculator;

    @Before
    public void setUp() {
        calculator = new PinballMatchCalculator();
    }

    @Test
    public void testEmptyString() {
        int pointsTotal = calculator.calculate("");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testNullString() {
        int pointsTotal = calculator.calculate(null);
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testOnlySpacesString() {
        int pointsTotal = calculator.calculate("    ");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testNotEnoughFrameBoundaries1() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testNotEnoughFrameBoundaries2() {
        int pointsTotal = calculator.calculate("X|");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testMustHaveBonusBallsFrameBoundary() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X|X|X|X|X|XX|");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testNotEnoughFrameBoundaries3() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X|X|X|X|X|XX");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testHappyPath() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X|X|X|X|X||XX");
        assertThat(pointsTotal).isEqualTo(300);
    }

    @Test
    public void testHappyPath1() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X|X|X|X|X||XX");
        assertThat(pointsTotal).isEqualTo(300);
    }

    @Test
    public void testHappyPath2() {
        int pointsTotal = calculator.calculate("9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||");
        assertThat(pointsTotal).isEqualTo(90);
    }

    @Test
    public void testHappyPath3() {
        int pointsTotal = calculator.calculate("5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5");
        assertThat(pointsTotal).isEqualTo(150);
    }

    @Test
    public void testHappyPath4() {
        int pointsTotal = calculator.calculate("X|7/|9-|X|-8|8/|-6|X|X|X||81");
        assertThat(pointsTotal).isEqualTo(167);
    }

    @Test
    public void testHappyPath5() {
        int pointsTotal = calculator.calculate("X|X|X|X|X|X|X|X|X|X||--");
        assertThat(pointsTotal).isEqualTo(270);
    }

    @Test
    public void testHappyPath6() {
        int pointsTotal = calculator.calculate("--|X|X|X|X|X|X|X|X|X||--");
        assertThat(pointsTotal).isEqualTo(240);
    }

    @Test
    public void testHappyPath7() {
        int pointsTotal = calculator.calculate("X|--|X|X|X|X|X|X|X|X||XX");
        assertThat(pointsTotal).isEqualTo(250);
    }

    @Test
    public void testContainsInvalidFrameValue() {
        int pointsTotal = calculator.calculate("X|//|X|X|X|X|X|X|X|X||XX");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testContainsInvalidFrameValue2() {
        int pointsTotal = calculator.calculate("|||||||||||");
        assertThat(pointsTotal).isEqualTo(-1);
    }

    @Test
    public void testContainsInvalidFrameValue3() {
        int pointsTotal = calculator.calculate("||||X|||||||");
        assertThat(pointsTotal).isEqualTo(-1);
    }
}
