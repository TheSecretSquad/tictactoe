package com.disalvo.peter.tictactoe.positionCollectionGroups;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;
import com.disalvo.peter.tictactoe.PositionCollectionGroup;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLengthColumn;

import java.util.Iterator;

public class Columns implements PositionCollectionGroup {

    private final int maximum;
    private final int minimum;

    public Columns(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public Iterator<PositionCollection> iterator() {
        return new ColumnRangeIterator(minimum, maximum);
    }

    private static class ColumnRangeIterator extends RangeIterator {

        public ColumnRangeIterator(int minimum, int maximum) {
            super(minimum, maximum);
        }

        @Override
        protected PositionCollection range(int current, int minimum, int maximum) {
            return new RangeOffsetAndLengthColumn(new Position(minimum, current), maximum);
        }
    }
}
