package com.disalvo.peter;

import java.util.HashMap;
import java.util.Map;
import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;

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

    public GameEndCondition condition(GameEndEvaluation evaluation, Mark mark) {
        return evaluation.condition(this, mark);
    }

    public <D extends Dimension> D dimensionFilledByMarkOrDefault(Dimensions<D> dimensions, Mark mark, D defaultDimension) {
        for(D dimension : dimensions) {
            if(dimension.isFilledWithMarkOnGrid(mark, this)) {
                return dimension;
            }
        }
        return defaultDimension;
    }

    interface Dimension {
        boolean isFilledWithMarkOnGrid(Mark mark, Grid grid);
    }

    interface Dimensions<T extends Dimension> extends Iterable<T> {

    }
}
