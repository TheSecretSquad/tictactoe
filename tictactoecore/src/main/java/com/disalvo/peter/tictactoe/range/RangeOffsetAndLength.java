package com.disalvo.peter.tictactoe.range;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;

import java.util.Iterator;

public class RangeOffsetAndLength implements PositionCollection {

    private final Position from;
    private final Offset offset;
    private final int length;

    public RangeOffsetAndLength(Position from, Offset offset, int length) {
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
                currentPosition = offset.apply(currentPosition);
                ++currentIteration;
                return next;
            }
        };
    }
}
