package org.example;

import java.util.Optional;

final class FrameImpl implements Frame {

    private final Throw firstThrow;
    private final Throw secondThrow;
    private final Throw bonusThrow;

     FrameImpl(final Throw firstThrow, final Throw secondThrow, final Throw bonusThrow) {
        this.firstThrow = firstThrow;
        this.secondThrow = secondThrow;
        this.bonusThrow = bonusThrow;
        if (!this.isValid()) throw new IllegalArgumentException("Not a valid Frame");
    }

    FrameImpl(final Throw firstThrow, final Throw secondThrow) {
        this(firstThrow, secondThrow, null);
    }

    FrameImpl(final Throw firstThrow) {
        this(firstThrow, null, null);
    }

    public int firstThrowScore() {
        return firstThrow.getPins();
    }

    public int combinedScore() {
        final int second = getScore(secondThrow);
        final int third = getScore(bonusThrow);
        return firstThrow.getPins() + second + third;
    }

    @Override
    public int frameScore(Frame next) {
        if (this.isSpare()) return this.combinedScore() + next.firstThrowScore();
        if (this.isStrike()) return this.combinedScore() + next.combinedScore();

        return this.combinedScore();
    }

    private int getScore(Throw opThrow) {
        return opThrow == null ? 0 : opThrow.getPins();
    }

    private boolean isValid() {
        return isStrike() || isSimple() || isBonus();
    }

    public boolean isStrike() {
        return isSingle() && firstThrow.getPins() == 10;
    }

    private boolean isSingle() {
        return secondThrow == null && bonusThrow == null;
    }

    private boolean isDouble() {
        return secondThrow != null && bonusThrow == null;
    }

    private boolean isTriple() {
        return secondThrow != null && bonusThrow != null;
    }

    public boolean isSimple() {
        if (isDouble()) {
            int pins = combinedScore();
            return (pins >= 0 && pins <= 10);
        }
        return false;
    }

    public boolean isBonus() {
        if (isTriple()) {
            if (firstThrow.getPins() == 10) return true; // Strike + 2
            if (firstThrow.getPins() + getScore(secondThrow) == 10) return true; // Spare + 1
        }
        return false;
    }

    public boolean isSpare() {
        return isSimple() && combinedScore() == 10;
    }
}
