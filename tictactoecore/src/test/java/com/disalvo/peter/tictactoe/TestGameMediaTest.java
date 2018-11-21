package com.disalvo.peter.tictactoe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TestGameMediaTest {

    @Test
    public void newMediaIsEmpty() {
        TestGameMedia subject = new TestGameMedia();

        assertFalse(subject.receivedMarkAtPosition(new Mark("x"), new Position(1, 1)));
        assertFalse(subject.hasSignificantPositions());
    }
}
