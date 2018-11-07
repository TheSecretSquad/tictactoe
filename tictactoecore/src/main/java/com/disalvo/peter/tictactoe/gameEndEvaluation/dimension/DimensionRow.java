package com.disalvo.peter.tictactoe.gameEndEvaluation.dimension;

import com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength.RangeOffsetAndLengthRow;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Dimension;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;
import com.disalvo.peter.tictactoe.Position;

import java.util.Iterator;

public class DimensionRow implements Dimension {

    private final int dimension;

    public DimensionRow(int dimension) {
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
        protected Range range(int current, int dimension) {
            return new RangeOffsetAndLengthRow(new Position(current, 1), dimension);
        }
    }
}
