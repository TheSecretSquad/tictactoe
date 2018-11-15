package com.disalvo.peter.tictactoe.evaluation.dimension;

import com.disalvo.peter.tictactoe.evaluation.range.RangeOffsetAndLengthRow;
import com.disalvo.peter.tictactoe.evaluation.Dimension;
import com.disalvo.peter.tictactoe.evaluation.Range;
import com.disalvo.peter.tictactoe.Position;

import java.util.Iterator;

public class DimensionRows implements Dimension {

    private final int dimension;

    public DimensionRows(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public Iterator<Range> iterator() {
        return new RowIterator(dimension);
    }

    private static class RowIterator extends DimensionIterator {

        public RowIterator(int dimension) {
            super(dimension);
        }

        @Override
        protected Range range(int currentDimension, int maxDimension) {
            return new RangeOffsetAndLengthRow(new Position(currentDimension, 1), maxDimension);
        }
    }
}
