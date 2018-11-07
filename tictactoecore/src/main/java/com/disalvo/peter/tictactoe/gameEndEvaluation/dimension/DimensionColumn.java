package com.disalvo.peter.tictactoe.gameEndEvaluation.dimension;

import com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength.RangeOffsetAndLengthColumn;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Dimension;
import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;
import com.disalvo.peter.tictactoe.Position;

import java.util.Iterator;

public class DimensionColumn implements Dimension {

    private final int dimension;

    public DimensionColumn(int dimension) {
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
        protected Range range(int current, int dimension) {
            return new RangeOffsetAndLengthColumn(new Position(1, current), dimension);
        }
    }
}
