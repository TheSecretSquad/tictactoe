package com.disalvo.peter.tictactoe;

import java.util.Iterator;
import static com.disalvo.peter.tictactoe.Position.Offset;

class RangeOffsetAndLength implements Range {

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

    static class Column implements Range {

        private final RangeOffsetAndLength range;

        public Column(Position from, int length) {
            range = new RangeOffsetAndLength(from, new OffsetRange(1, 0), length);
        }

        @Override
        public Iterator<Position> iterator() {
            return range.iterator();
        }
    }

    static class Row implements Range {

        private final RangeOffsetAndLength range;

        public Row(Position from, int length) {
            range = new RangeOffsetAndLength(from, new OffsetRange(0, 1), length);
        }

        @Override
        public Iterator<Position> iterator() {
            return range.iterator();
        }
    }

    static class OffsetRange implements Offset {
        private final int row;
        private final int column;

        public OffsetRange(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public Position apply(Position position) {
            return position.offsetBy(row, column);
        }
    }
}
