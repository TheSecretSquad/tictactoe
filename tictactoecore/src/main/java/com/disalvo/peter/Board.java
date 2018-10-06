package com.disalvo.peter;

import java.util.HashMap;
import java.util.Map;

class Board {
    private static final int Size = 9;

    private final Map<Position, Mark> positions;

    public Board() {
        this(new HashMap<>());
    }

    private Board(Map<Position, Mark> positions) {
        this.positions = positions;
    }

    public boolean isEmptyPosition(Position position) {
        return !positions.containsKey(position);
    }

    public Board withMarkAtPosition(Mark mark, Position position) {
        Map<Position, Mark> newPositions = new HashMap<>(positions);
        newPositions.put(position, mark);
        return new Board(newPositions);
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
}
