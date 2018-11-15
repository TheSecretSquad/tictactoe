package com.disalvo.peter.tictactoe.evaluation.dimension;

import com.disalvo.peter.tictactoe.evaluation.Range;

import java.util.Iterator;

abstract class DimensionIterator implements Iterator<Range> {

    private final int maxDimension;
    private int currentDimension;

    public DimensionIterator(int maxDimension) {
        this.maxDimension = maxDimension;
        this.currentDimension = 1;
    }

    @Override
    public boolean hasNext() {
        return currentDimension <= maxDimension;
    }

    @Override
    public Range next() {
        Range next = range(currentDimension, maxDimension);
        ++currentDimension;
        return next;
    }

    protected abstract Range range(int currentDimension, int maxDimension);
}
