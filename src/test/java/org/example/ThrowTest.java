package org.example;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ThrowTest
{

    @Test
    public void testSimpleValidThrow() {
        final Throw t = new ThrowImpl(4);
    }

    @Test
    public void testZeroValidThrow() {
        final Throw t = new ThrowImpl(0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInValidThrow() {
        final Throw t = new ThrowImpl(34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSimpleInValidThrowNeg() {
        final Throw t = new ThrowImpl(-9);
    }
}

