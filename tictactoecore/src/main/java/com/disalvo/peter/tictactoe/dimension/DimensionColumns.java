package com.disalvo.peter.tictactoe.dimension;

import com.disalvo.peter.tictactoe.Dimension;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLengthColumn;

import java.util.Iterator;

public class DimensionColumns implements Dimension {

    private final int size;

    public DimensionColumns(int size) {
        this.size = size;
    }

    @Override
    public Iterator<Range> iterator() {
        return new ColumnIterator(size);
    }

    private static class ColumnIterator extends DimensionIterator {

        public ColumnIterator(int size) {
            super(size);
        }

        @Override
        protected Range range(int currentRange, int dimensionSize) {
            return new RangeOffsetAndLengthColumn(new Position(1, currentRange), dimensionSize);
        }
    }
}
