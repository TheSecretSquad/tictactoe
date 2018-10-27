package com.disalvo.peter;

import java.util.HashMap;
import java.util.Map;

public class Grid {
    private final int size;

    private final Map<Position, Mark> positions;

    public Grid(int size) {
        this(size, new HashMap<>());
    }

    private Grid(int size, Map<Position, Mark> positions) {
        this.size = size;
        this.positions = positions;
    }

    public boolean isEmptyPosition(Position position) {
        return !positions.containsKey(position);
    }

    public Grid withMarkAtPosition(Mark mark, Position position) {
        Map<Position, Mark> newPositions = new HashMap<>(positions);
        newPositions.put(position, mark);
        return new Grid(size, newPositions);
    }

    private Mark markAtPosition(Position position) {
        return positions.get(position);
    }

    public boolean isFilled() {
        return positions.size() == size;
    }

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    public <T> T evaluationResult(GridEvaluation<T> evaluation, Mark mark) {
        return evaluation.result(this, mark);
    }

    public <D extends Dimension> D firstDimensionFilledWithMarkOrDefault(Dimensions<D> dimensions, Mark mark, D defaultDimension) {
        for(D dimension : dimensions) {
            if(dimension.isFilledWithMarkOnGrid(mark, this))
                return dimension;
        }
        return defaultDimension;
    }

    interface Dimension {
        boolean isFilledWithMarkOnGrid(Mark mark, Grid grid);
    }

    interface Dimensions<D extends Dimension> extends Iterable<D> {

    }

    interface GridEvaluation<T> {

        T result(Grid grid, Mark mark);
    }
}
