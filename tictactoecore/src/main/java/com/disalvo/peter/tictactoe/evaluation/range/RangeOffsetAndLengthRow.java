package com.disalvo.peter.tictactoe.evaluation.range;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.evaluation.Range;

import java.util.Iterator;

public class RangeOffsetAndLengthRow implements Range {

    private final RangeOffsetAndLength range;

    public RangeOffsetAndLengthRow(Position from, int length) {
        range = new RangeOffsetAndLength(from, new Offset(0, 1), length);
    }

    @Override
    public Iterator<Position> iterator() {
        return range.iterator();
    }
}
