package com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;

import java.util.Iterator;

public class RangeOffsetAndLengthColumn implements Range {

    private final RangeOffsetAndLength range;

    public RangeOffsetAndLengthColumn(Position from, int length) {
        range = new RangeOffsetAndLength(from, new OffsetRange(1, 0), length);
    }

    @Override
    public Iterator<Position> iterator() {
        return range.iterator();
    }
}
