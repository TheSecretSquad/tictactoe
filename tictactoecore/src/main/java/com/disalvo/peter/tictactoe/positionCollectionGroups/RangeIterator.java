package com.disalvo.peter.tictactoe.positionCollectionGroups;

import com.disalvo.peter.tictactoe.PositionCollection;

import java.util.Iterator;

abstract class RangeIterator implements Iterator<PositionCollection> {

    private final int minimum;
    private final int maximum;
    private int current;

    public RangeIterator(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.current = 1;
    }

    @Override
    public boolean hasNext() {
        return current <= maximum;
    }

    @Override
    public PositionCollection next() {
        PositionCollection next = range(current, minimum, maximum);
        ++current;
        return next;
    }

    protected abstract PositionCollection range(int current, int minimum, int maximum);
}
