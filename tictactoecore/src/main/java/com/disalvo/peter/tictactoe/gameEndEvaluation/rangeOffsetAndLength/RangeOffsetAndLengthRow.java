package com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;

import java.util.Iterator;

public class RangeOffsetAndLengthRow implements Range {

    private final RangeOffsetAndLength range;

    public RangeOffsetAndLengthRow(Position from, int length) {
        range = new RangeOffsetAndLength(from, new OffsetRange(0, 1), length);
    }

    @Override
    public Iterator<Position> iterator() {
        return range.iterator();
    }
}
