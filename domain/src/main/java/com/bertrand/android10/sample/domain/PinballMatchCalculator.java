package com.bertrand.android10.sample.domain;

import java.util.Arrays;

public class PinballMatchCalculator {

    public int calculate(String pinballMatch) {
        if (pinballMatch == null || pinballMatch.trim().length() <= 0 || invalidFormat(pinballMatch)) {
            return -1;
        } else {
            return algorithm(pinballMatch);
        }
    }

    private int algorithm(String pinballMatch) {
        String[] frameScoreSplitFromBonusBalls = pinballMatch.split("\\|\\|");
        String[] frameScores = frameScoreSplitFromBonusBalls[0].split("\\|");
        String[] all = frameScores;
        if (frameScoreSplitFromBonusBalls.length == 2) {
            String[] bonusBalls = frameScoreSplitFromBonusBalls[1].split("");
            all = concat(frameScores, bonusBalls);
        }
        return calculateFrameScores(all);
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    private int calculateFrameScores(String[] frameScores) {
        int totalSum = 0;
        for (int i = 0; i < frameScores.length; i++) {
            totalSum = totalSum + sumOfIndex(frameScores, i);
        }
        return totalSum;
    }

    private int sumOfIndex(String[] frameScores, int index) {
        int sum = 0;
        sum = sumOfIndex(frameScores, index, sum);
        return sum;
    }


    private int sumOfIndex(String[] frameScores, int index, int sum) {
        if (index == 10 || index == 11) {
            // handled already
        } else {
            if (frameScores[index].equals("X")) {
                sum = 10;
                sum = getLaterValuesOnX(frameScores, index, sum);
            } else if (frameScores[index].equals("|")) {
                // do nothing
            } else if (frameScores[index].contains("-")) {
                sum = getOtherValueOnmiss(frameScores[index], sum);
            } else if (frameScores[index].contains("/")) {
                sum += 10;
                //If the second ball in a frame knocks down all ten pins, this is called a &quot;spare&quot;.
                // The frame is over. The score
                // for the frame is ten plus the number of pins knocked down in the next ball.
                if (index + 1 < frameScores.length) {
                    if (frameScores[index + 1].equals("X")) {
                        sum = sum + 10;
                    } else if (frameScores[index + 1].equals("|") || frameScores[index + 1].equals("-")) {
                        // do nothing
                    } else {
                        String[] ballsInFrame = frameScores[index + 1].split("");
                        if (ballsInFrame[0].equals("/")) {
                            // do nothing
                        } else if (ballsInFrame[0].equals("-")) {
                            // do nothing
                        } else {
                            sum = sum + Integer.valueOf(ballsInFrame[0]);
                        }
                    }
                }

            } else {
                sum = sum + Integer.valueOf(frameScores[index]);
            }
        }

        return sum;
    }

    private int getLaterValuesOnX(String[] frameScores, int index, int sum) {
        if (index + 1 < frameScores.length) {
            if (frameScores[index + 1].equals("X")) {
                sum += 10;
            } else if (frameScores[index + 1].equals("|") || frameScores[index + 1].equals("-")) {
                // do nothing
            } else {
                String[] ballsInFrame = frameScores[index + 1].split("");
                if (ballsInFrame[0].equals("/")) {
                    // do nothing
                } else if (ballsInFrame[0].equals("-")) {
                    if(ballsInFrame[1].equals("-")) {
                        // entire frame was missed
                    } else {
                        sum = sum + Integer.valueOf(ballsInFrame[1]);
                    }
                } else {
                    sum = sum + Integer.valueOf(ballsInFrame[0]);
                    if(ballsInFrame.length == 2 && ballsInFrame[1].equals("/")) {
                        int remainder = 10 - Integer.valueOf(ballsInFrame[0]);
                        sum = sum + remainder;
                    }
                }
            }
        }

        if (index + 2 < frameScores.length) {
            String[] ballsInFrame = frameScores[index + 1].split("");
            if (ballsInFrame[0].equals("-")) {
                // handled already
            } else if(ballsInFrame.length == 2 && ballsInFrame[1].equals("/")) {

            } else {
                if (frameScores[index + 2].equals("X")) {
                    sum = sum + 10;
                } else if (frameScores[index + 2].equals("|") || frameScores[index + 2].equals("-")) {
                    // do nothing
                } else {
                    if (frameScores[index + 2].contains("/")) {
                        sum += 10;
                    } else if (frameScores[index + 2].contains("-")) {
                        String[] ballsInFrame2 = frameScores[index + 2].split("");
                        if (ballsInFrame2[0].equals("/")) {
                            // do nothing
                        } else if (ballsInFrame2[0].equals("-")) {
                            // do nothing
                        } else {
                            sum = sum + Integer.valueOf(ballsInFrame2[0]);
                        }
                    } else {
                        sum = sum + Integer.valueOf(frameScores[index + 2]);
                    }
                }
            }
        }


        return sum;
    }

    private int getOtherValueOnmiss(String frameScore1, int sum) {
        String[] frameScore = frameScore1.split("");
        if (frameScore[0].equals("-")) {
            if(frameScore[1].equals("-")) {
                // entire frame was missed
            } else {
                sum = sum + Integer.valueOf(frameScore[1]);
            }
        } else {
            sum = sum + Integer.valueOf(frameScore[0]);
        }
        return sum;
    }

    private boolean invalidFormat(String pinballMatch) {
        // rule 1: there must be enough frame boundaries
        int numberOfFrameBoundaries = pinballMatch.length() - pinballMatch.replace("|", "").length();
        // rule 2 :there must be a bonus balls frame boundary, even if there are no bonus balls
        boolean containsBonusBallFrameBoundary = pinballMatch.contains("||");
        String[] frameScoreSplitFromBonusBalls = pinballMatch.split("\\|\\|");
        boolean containsInvalidFrameValue = pinballMatch.contains("//");
        if (numberOfFrameBoundaries != 11
                || !containsBonusBallFrameBoundary
                || invalidLength(frameScoreSplitFromBonusBalls)
                || containsInvalidFrameValue) {
            return true;
        } else {
            return false;
        }
    }

    private boolean invalidLength(String[] frameScoreSplitFromBonusBalls) {
        return frameScoreSplitFromBonusBalls.length != 1 && frameScoreSplitFromBonusBalls.length != 2;
    }
}
