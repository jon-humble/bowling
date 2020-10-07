package org.example;

import org.junit.Test;
import static org.example.FrameTest.*;
import static org.junit.Assert.assertTrue;

public class LineTest {

    private final Frame strike = Frame.strike();
    private final Frame spare1 = Frame.simple(five, five);
    private final Frame spare2 = Frame.simple(zero, ten);
    private final Frame simple1 = Frame.simple(two, five);
    private final Frame simple2 = Frame.simple(seven, two);
    private final Frame gutter = Frame.simple(zero, zero);
    private final Frame bonusStrike = Frame.bonus(ten, five, two);
    private final Frame bonusSpare = Frame.bonus(five, five, seven);


    @Test
    public void testSimpleValidLine() {
        final LineImpl line = new LineImpl(strike, simple1, simple1, simple1, spare1, simple1, simple1, simple1, simple1, gutter);
        assertTrue(line.score() == 17 + (7 * 7) + 12);
    }

    @Test
    public void testSimpleValidLineBonus() {
        final LineImpl line = new LineImpl(strike, spare1, spare2, simple1, simple2, gutter, strike, strike, spare1, bonusSpare);
        assertTrue(line.score() == 20 + 10 + 12 + 7 + 9 + 0 + 20 + 20 + 15 + 17);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidLineTooFew() {
        final Line line = new LineImpl(strike, spare1, spare2, simple1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidLineTooManyBonuses() {
        final Line line = new LineImpl(strike, bonusSpare, spare2, simple1, simple2, gutter, strike, strike, bonusSpare, bonusStrike);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidLineBonusNotLast() {
        final Line line = new LineImpl(strike, bonusSpare, spare2, simple1, simple2, gutter, strike, strike, spare1, strike);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidNoBonusSpare() {
        final Line line = new LineImpl(strike, spare1, spare2, simple1, simple2, gutter, strike, strike, spare1, spare1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidNoBonusStrike() {
        final Line line = new LineImpl(strike, spare1, spare2, simple1, simple2, gutter, strike, strike, spare1, strike);
    }

}
