package com.disalvo.peter.tictactoe.gameEndEvaluation.dimension;

import com.disalvo.peter.tictactoe.gameEndEvaluation.Range;

import java.util.Iterator;

abstract class DimensionIterator implements Iterator<Range> {

    private final int dimension;
    private int current;

    public DimensionIterator(int dimension) {
        this.dimension = dimension;
        this.current = 1;
    }

    @Override
    public boolean hasNext() {
        return current <= dimension;
    }

    @Override
    public Range next() {
        Range next = range(current, dimension);
        ++current;
        return next;
    }

    protected abstract Range range(int current, int dimension);
}
