package com.disalvo.peter;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int BoardSize = 9;

    private final Map<Position, Mark> positions;

    public Board() {
        this.positions = new HashMap<>();
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

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    private Mark markAtPosition(Position position) {
        return this.positions.get(position);
    }

    public boolean isFilled() {
        return this.positions.size() == BoardSize;
    }
}
