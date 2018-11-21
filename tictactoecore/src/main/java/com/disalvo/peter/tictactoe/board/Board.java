package com.disalvo.peter.tictactoe.board;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.dimension.DimensionRows;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class Board {
    private static final int DEFAULT_SIZE = 3;

    private final int size;
    private final int numberOfSquares;
    private final Map<Position, Mark> positions;

    public Board() {
        this(DEFAULT_SIZE, new HashMap<>());
    }

    public Board(int size) {
        this(size, new HashMap<>());
    }

    private Board(int size, Map<Position, Mark> positions) {
        this.size = size;
        this.numberOfSquares = size * size;
        this.positions = positions;
    }

    public Board withMarkAtPosition(Mark mark, Position position) {
        Map<Position, Mark> newPositions = new HashMap<>(positions);
        newPositions.put(position, mark);
        return new Board(size, newPositions);
    }

    private Mark markAtPosition(Position position) {
        return positions.get(position);
    }

    public boolean isFilled() {
        return positions.size() == numberOfSquares;
    }

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    public boolean arePositionsOccupiedByMark(PositionCollection positionCollection, Mark mark) {
        return StreamSupport.stream(positionCollection.spliterator(), true).allMatch(p -> isPositionOccupiedByMark(p, mark));
    }

    public boolean isEmptyPosition(Position position) {
        return !isOccupied(position);
    }

    public <T> T evaluationResult(BoardEvaluation<T> evaluation, Mark mark) {
        return evaluation.result(this, mark, size);
    }

    public Board printOn(BoardMedia boardMedia) {
        for(Range range : new DimensionRows(size)) {
            for(Position position : range) {
                printPositionOn(position, boardMedia);
            }
        }
        return this;
    }

    private void printPositionOn(Position position, BoardMedia boardMedia) {
        if(isOccupied(position)) {
            boardMedia.printMarkAtPosition(markAtPosition(position), position);
        }
        else {
            boardMedia.printEmptyPosition(position);
        }
    }

    private boolean isOccupied(Position position) {
        return positions.containsKey(position);
    }
}
