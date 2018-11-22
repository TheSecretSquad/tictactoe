package com.disalvo.peter.tictactoe.dimension;

import com.disalvo.peter.tictactoe.Range;

import java.util.Iterator;

abstract class DimensionIterator implements Iterator<Range> {

    private final int size;
    private int currentRange;

    public DimensionIterator(int size) {
        this.size = size;
        this.currentRange = 1;
    }

    @Override
    public boolean hasNext() {
        return currentRange <= size;
    }

    @Override
    public Range next() {
        Range next = range(currentRange, size);
        ++currentRange;
        return next;
    }

    protected abstract Range range(int currentRange, int dimensionSize);
}
