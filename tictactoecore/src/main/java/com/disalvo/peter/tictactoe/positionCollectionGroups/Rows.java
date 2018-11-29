package com.disalvo.peter.tictactoe.positionCollectionGroups;

import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.PositionCollection;
import com.disalvo.peter.tictactoe.PositionCollectionGroup;
import com.disalvo.peter.tictactoe.range.RangeOffsetAndLengthRow;

import java.util.Iterator;

public class Rows implements PositionCollectionGroup {

    private final int maximum;
    private final int minimum;

    public Rows(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public Iterator<PositionCollection> iterator() {
        return new RowsIterator(minimum, maximum);
    }

    private static class RowsIterator extends RangeIterator {

        public RowsIterator(int minimum, int maximum) {
            super(minimum, maximum);
        }

        @Override
        protected PositionCollection range(int current, int minimum, int maximum) {
            return new RangeOffsetAndLengthRow(new Position(current, minimum), maximum);
        }
    }
}
