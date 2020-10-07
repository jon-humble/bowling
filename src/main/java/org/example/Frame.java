package org.example;

public interface Frame {

    public static Frame strike() {
        return Factory.strike();
    }

    public static Frame simple(final Throw first, final Throw second) {
        return Factory.simple(first, second);
    }

    public static Frame bonus(final Throw first, final Throw second, final Throw third) {
        return Factory.bonus(first, second, third);
    }

    public static Frame gutter() {
        return Factory.gutter();
    }

    public int firstThrowScore();

    public int combinedScore();

    public int frameScore(final Frame next);

    public boolean isStrike();

    public boolean isSimple();

    public boolean isBonus();

    public boolean isSpare();
}
