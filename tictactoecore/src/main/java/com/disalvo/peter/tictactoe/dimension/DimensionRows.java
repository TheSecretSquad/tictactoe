package com.disalvo.peter.tictactoe.dimension;

import com.disalvo.peter.tictactoe.Dimension;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLengthRow;

import java.util.Iterator;

public class DimensionRows implements Dimension {

    private final int length;

    public DimensionRows(int length) {
        this.length = length;
    }

    @Override
    public Iterator<Range> iterator() {
        return new RowIterator(length);
    }

    private static class RowIterator extends DimensionIterator {

        public RowIterator(int length) {
            super(length);
        }

        @Override
        protected Range range(int currentRange, int dimensionSize) {
            return new RangeOffsetAndLengthRow(new Position(currentRange, 1), dimensionSize);
        }
    }
}
