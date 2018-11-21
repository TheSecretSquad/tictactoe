package com.disalvo.peter.tictactoe.dimension;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.Dimension;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLengthColumn;

import java.util.Iterator;

public class DimensionColumns implements Dimension {

    private final int dimension;

    public DimensionColumns(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public Iterator<Range> iterator() {
        return new ColumnIterator(dimension);
    }

    private static class ColumnIterator extends DimensionIterator {

        public ColumnIterator(int dimension) {
            super(dimension);
        }

        @Override
        protected Range range(int currentDimension, int maxDimension) {
            return new RangeOffsetAndLengthColumn(new Position(1, currentDimension), maxDimension);
        }
    }
}
