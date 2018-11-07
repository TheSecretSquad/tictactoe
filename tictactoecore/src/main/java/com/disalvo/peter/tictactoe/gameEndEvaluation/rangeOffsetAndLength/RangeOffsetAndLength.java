package com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;

import java.util.Iterator;

public class RangeOffsetAndLength implements Range {

    private final Position from;
    private final OffsetRange offset;
    private final int length;

    public RangeOffsetAndLength(Position from, OffsetRange offset, int length) {
        this.from = from;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public Iterator<Position> iterator() {
        return new Iterator<Position>() {
            int currentIteration = 1;
            Position currentPosition = from;

            @Override
            public boolean hasNext() {
                return currentIteration <= length;
            }

            @Override
            public Position next() {
                Position next = currentPosition;
                currentPosition = currentPosition.offsetBy(offset);
                ++currentIteration;
                return next;
            }
        };
    }

}
