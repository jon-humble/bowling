package org.example;

final class ThrowImpl implements Throw {

    private final int pins;

    ThrowImpl(int pins) {
        if (!Throw.pinsAreValid(pins)) throw new IllegalArgumentException(pins + " pins is an invalid amount. A throw can take out 0..10 pins");
        this.pins = pins;
    }

    @Override
    public int getPins() {
        return pins;
    }

}
