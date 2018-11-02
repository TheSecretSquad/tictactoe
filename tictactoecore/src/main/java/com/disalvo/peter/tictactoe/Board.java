package com.disalvo.peter.tictactoe;

import java.util.HashMap;
import java.util.Map;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;

public class Board {
    private static final int DEFAULT_SIZE = 9;

    private final int size;
    private final Map<Position, Mark> positions;

    public Board() {
        this(DEFAULT_SIZE, new HashMap<>());
    }

    public Board(int size) {
        this(size, new HashMap<>());
    }

    private Board(int size, Map<Position, Mark> positions) {
        this.size = size;
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
        return positions.size() == size;
    }

    public boolean isPositionOccupiedByMark(Position position, Mark mark) {
        return mark.equals(markAtPosition(position));
    }

    public BoardCondition evaluationResult(BoardEvaluation evaluation, Mark mark) {
        return evaluation.result(this, mark);
    }

    public <D extends Dimension> D firstDimensionFilledWithMarkOrDefault(Dimensions<D> dimensions, Mark mark, D defaultDimension) {
        for(D dimension : dimensions) {
            if(dimension.isFilledWithMarkOnGrid(mark, this))
                return dimension;
        }
        return defaultDimension;
    }

    public Play validatedPlay(Play play, Position position) {
        return isEmptyPosition(position) ? play.validPosition() : play;
    }

    private boolean isEmptyPosition(Position position) {
        return !positions.containsKey(position);
    }

    interface Dimension {
        boolean isFilledWithMarkOnGrid(Mark mark, Board board);
    }

    interface Dimensions<D extends Dimension> extends Iterable<D> {

    }

    interface BoardEvaluation {

        BoardCondition result(Board board, Mark mark);
    }
}
