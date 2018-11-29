package com.disalvo.peter.tictactoe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGameMedia implements GameMedia {
    private final Map<Position, Mark> positions;
    private final List<Position> significantPositions;

    public TestGameMedia() {
        this.positions = new HashMap<>();
        this.significantPositions = null;
    }

    public boolean receivedMarkAtPosition(Mark mark, Position position) {
        return positions.containsKey(position) && positions.get(position).equals(mark);
    }

    public boolean isSignificantPositions(PositionCollection positionCollection) {
        return significantPositions.equals(positionCollection);
    }

    @Override
    public BoardConditionMedia printSignificantPositions(PositionCollection positionCollection) {
        return null;
    }

    @Override
    public BoardMedia printMarkAtPosition(Mark mark, Position position) {
        return null;
    }

    @Override
    public BoardMedia printEmptyPosition(Position position) {
        return null;
    }

    public boolean hasSignificantPositions() {
        return significantPositions != null;
    }
}
