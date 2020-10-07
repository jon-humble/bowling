package org.example;

public interface Throw {

    static boolean pinsAreValid(int pins) {
        return (pins >= 0 && pins <= 10);
    }

    int getPins();
}
