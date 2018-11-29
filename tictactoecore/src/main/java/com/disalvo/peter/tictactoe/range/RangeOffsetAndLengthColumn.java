package com.disalvo.peter.tictactoe.range;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;

import java.util.Iterator;

public class RangeOffsetAndLengthColumn implements PositionCollection {

    private final RangeOffsetAndLength range;

    public RangeOffsetAndLengthColumn(Position from, int length) {
        range = new RangeOffsetAndLength(from, new Offset(1, 0), length);
    }

    @Override
    public Iterator<Position> iterator() {
        return range.iterator();
    }
}
