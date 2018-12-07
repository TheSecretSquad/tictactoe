package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.positionCollectionGroups.Columns;
import com.disalvo.peter.tictactoe.positionCollectionGroups.Diagonals;
import com.disalvo.peter.tictactoe.positionCollectionGroups.Rows;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class Board {
    private static final int DEFAULT_SIZE = 3;
    private static final int MINIMUM_POSITION_VALUE = 1;

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

    private boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    private boolean arePositionsOccupiedByMark(PositionCollection positionCollection, Mark mark) {
        return StreamSupport.stream(positionCollection.spliterator(), true).allMatch(p -> isPositionOccupiedByMark(p, mark));
    }

    private boolean isOccupied(Position position) {
        return positions.containsKey(position);
    }

    public PositionCollectionGroup rows() {
        return new Rows(MINIMUM_POSITION_VALUE, size);
    }

    public PositionCollectionGroup columns() {
        return new Columns(MINIMUM_POSITION_VALUE, size);
    }

    public PositionCollectionGroup diagonals() {
        return new Diagonals(MINIMUM_POSITION_VALUE, size);
    }

    public <T> T positionCollectionFilledWithMark(
            PositionCollectionGroup positionCollectionGroup,
            Mark mark,
            OnPositionCollectionNotFound<T> onNotFound,
            OnPositionCollectionFound<T> onFound) {

        for (PositionCollection positionCollection : positionCollectionGroup) {
            if (arePositionsOccupiedByMark(positionCollection, mark)) {
                return onFound.found(positionCollection);
            }
        }

        return onNotFound.notFound();
    }

    public Board validate(Position position, BoardValidationListener boardValidationListener) {
        if(!isOccupied(position))
            boardValidationListener.validPosition();
        else
            boardValidationListener.invalidPosition();

        return this;
    }

    @FunctionalInterface
    public interface OnPositionCollectionNotFound<T> {
        T notFound();
    }

    @FunctionalInterface
    public interface OnPositionCollectionFound<T> {
        T found(PositionCollection range);
    }

    public Board printOn(BoardMedia boardMedia) {
        for (PositionCollection range : new Rows(1, size)) {
            for (Position position : range) {
                printPositionOn(position, boardMedia);
            }
        }
        return this;
    }

    private void printPositionOn(Position position, BoardMedia boardMedia) {
        if (isOccupied(position)) {
            boardMedia.printMarkAtPosition(markAtPosition(position), position);
        } else {
            boardMedia.printEmptyPosition(position);
        }
    }

    public interface BoardValidationListener {
        BoardValidationListener validPosition();

        BoardValidationListener invalidPosition();
    }
}
