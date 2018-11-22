package com.disalvo.peter.tictactoe.board;

import com.disalvo.peter.tictactoe.Dimension;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.dimension.DimensionColumns;
import com.disalvo.peter.tictactoe.dimension.DimensionRows;
import com.disalvo.peter.tictactoe.range.Offset;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    private boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    private boolean arePositionsOccupiedByMark(PositionCollection positionCollection, Mark mark) {
        return StreamSupport.stream(positionCollection.spliterator(), true).allMatch(p -> isPositionOccupiedByMark(p, mark));
    }

    public boolean isEmptyPosition(Position position) {
        return !isOccupied(position);
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

    private Position topLeft() {
        return new Position(1, 1);
    }

    private Position topRight() {
        return new Position(1, size);
    }

    private Range range(Position from, Offset offset) {
        return from.range(offset, size);
    }

    public Optional<Range> diagonalRangeFilledWithMark(Mark mark) {
        Range topLeftToBottomRight = range(topLeft(), new Offset(1, 1));
        Range topRightToBottomLeft = range(topRight(), new Offset(1, -1));

        if (arePositionsOccupiedByMark(topLeftToBottomRight, mark)) {
            return Optional.of(topLeftToBottomRight);
        }

        if (arePositionsOccupiedByMark(topRightToBottomLeft, mark)) {
            return Optional.of(topRightToBottomLeft);
        }

        return Optional.empty();
    }

    public Optional<Range> rowRangeFilledWithMark(Mark mark) {
        return rangeFilledWithMarkInDimension(mark, new DimensionRows(size));
    }

    public Optional<Range> columnRangeFilledWithMark(Mark mark) {
        return rangeFilledWithMarkInDimension(mark, new DimensionColumns(size));
    }

    private Optional<Range> rangeFilledWithMarkInDimension(Mark mark, Dimension dimension) {
        for(Range range : dimension) {
            if(arePositionsOccupiedByMark(range, mark)) {
                return Optional.of(range);
            }
        }

        return Optional.empty();
    }
}
