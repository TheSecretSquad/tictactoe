package com.disalvo.peter.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int DEFAULT_DIMENSION = 3;

    private final int dimension;
    private final Map<Position, Mark> positions;

    public Board() {
        this(DEFAULT_DIMENSION, new HashMap<>());
    }

    public Board(int dimension) {
        this(dimension, new HashMap<>());
    }

    private Board(int dimension, Map<Position, Mark> positions) {
        this.dimension = dimension;
        this.positions = positions;
    }

    public Board withMarkAtPosition(Mark mark, Position position) {
        Map<Position, Mark> newPositions = new HashMap<>(positions);
        newPositions.put(position, mark);
        return new Board(dimension, newPositions);
    }

    private Mark markAtPosition(Position position) {
        return positions.get(position);
    }

    public boolean isFilled() {
        return positions.size() == dimension * dimension;
    }

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    public boolean isEmptyPosition(Position position) {
        return !positions.containsKey(position);
    }

    public <T> T evaluationResult(BoardEvaluation<T> evaluation, Mark mark) {
        return evaluation.result(this, mark, dimension);
    }

    interface BoardEvaluation<T> {

        T result(Board board, Mark mark, int dimension);
    }
}
