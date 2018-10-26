package com.disalvo.peter;

import java.util.HashMap;
import java.util.Map;
import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;

public class Grid {
    private static final int Size = 9;

    private final Map<Position, Mark> positions;

    public Grid() {
        this(new HashMap<>());
    }

    private Grid(Map<Position, Mark> positions) {
        this.positions = positions;
    }

    public boolean isEmptyPosition(Position position) {
        return !positions.containsKey(position);
    }

    public Grid withMarkAtPosition(Mark mark, Position position) {
        Map<Position, Mark> newPositions = new HashMap<>(positions);
        newPositions.put(position, mark);
        return new Grid(newPositions);
    }

    private Mark markAtPosition(Position position) {
        return positions.get(position);
    }

    public boolean isFilled() {
        return positions.size() == Size;
    }

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    public GameEndCondition condition(GameEndEvaluation evaluation, Mark mark) {
        return evaluation.condition(this, mark);
    }
}
