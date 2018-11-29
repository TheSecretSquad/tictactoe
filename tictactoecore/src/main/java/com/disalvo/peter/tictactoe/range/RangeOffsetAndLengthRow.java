package com.disalvo.peter.tictactoe.range;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;

import java.util.Iterator;

public class RangeOffsetAndLengthRow implements PositionCollection {

    private final RangeOffsetAndLength range;

    public RangeOffsetAndLengthRow(Position from, int length) {
        range = new RangeOffsetAndLength(from, new Offset(0, 1), length);
    }

    @Override
    public Iterator<Position> iterator() {
        return range.iterator();
    }
}
