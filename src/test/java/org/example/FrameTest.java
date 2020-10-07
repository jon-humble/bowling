package org.example;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class FrameTest
{
    static Throw ten = new ThrowImpl(10);
    static Throw zero = new ThrowImpl(0);
    static Throw five = new ThrowImpl(5);
    static Throw two = new ThrowImpl(2);
    static Throw seven = new ThrowImpl(7);

    @Test
    public void testSimpleValidFrameStrike() {
        final Frame f = Frame.strike();
        assertTrue(f.isStrike());
        assertTrue(f.firstThrowScore() == 10);
        assertTrue(f.combinedScore() == 10);
        assertFalse(f.isSpare());
        assertFalse(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameSimple() {
        final Frame f = Frame.simple(two, five);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 2);
        assertTrue(f.combinedScore() == 7);
        assertFalse(f.isSpare());
        assertFalse(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameSpare() {
        final Frame f = Frame.simple(zero, ten);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 0);
        assertTrue(f.combinedScore() == 10);
        assertTrue(f.isSpare());
        assertFalse(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameSpare2() {
        final Frame f = Frame.simple(five, five);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 5);
        assertTrue(f.combinedScore() == 10);
        assertTrue(f.isSpare());
        assertFalse(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameBonusSpare() {
        final Frame f = Frame.bonus(five, five, two);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 5);
        assertTrue(f.combinedScore() == 12);
        assertFalse(f.isSpare());
        assertTrue(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameBonusStrike() {
        final Frame f = Frame.bonus(ten, two, five);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 10);
        assertTrue(f.combinedScore() == 17);
        assertFalse(f.isSpare());
        assertTrue(f.isBonus());
    }

    @Test
    public void testSimpleValidFrameBonusStrikes() {
        final Frame f = Frame.bonus(ten, ten, ten);
        assertFalse(f.isStrike());
        assertTrue(f.firstThrowScore() == 10);
        assertTrue(f.combinedScore() == 30);
        assertFalse(f.isSpare());
        assertTrue(f.isBonus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidFrameSimple() {
        final Frame f = Frame.simple(seven, five);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidFrameBonus() {
        final Frame f = Frame.bonus(seven, five, two); // Too many in first two throws
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInvalidFrameBonus2() {
        final Frame f = Frame.bonus(two, five, two); // Too few in first two throws
    }
}

