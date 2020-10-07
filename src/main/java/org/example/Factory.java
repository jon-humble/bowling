package org.example;

class Factory {

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract("_ -> new")
    static Throw createThrow(int pins) {
        return new ThrowImpl(pins);
    }

    static Frame strike() {
        return new FrameImpl(new ThrowImpl(10));
    }

    static Frame simple(final Throw first, final Throw second) {
        return new FrameImpl(first, second);
    }

    static Frame bonus(final Throw first, final Throw second, final Throw third) {
        return new FrameImpl(first, second, third);
    }

    static Frame gutter() {
        final Throw zero = new ThrowImpl(0);
        return new FrameImpl(zero, zero);
    }


}
