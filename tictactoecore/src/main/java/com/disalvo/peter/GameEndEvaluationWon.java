package com.disalvo.peter;

import java.util.Arrays;
import java.util.List;

class GameEndEvaluationWon extends GameEndEvaluationChain {
    private static final Dimension LeftColumnDimension() { return new ColumnDimension(1); }
    private static final Dimension CenterColumnDimension() { return new ColumnDimension(2); }
    private static final Dimension RightColumnDimension() { return new ColumnDimension(3); }
    private static final Dimension TopRowDimension() { return new RowDimension(1); }
    private static final Dimension MiddleRowDimension() { return new RowDimension(2); }
    private static final Dimension BottomRowDimension() { return new RowDimension(3); }
    private static Dimension TopLeftToBottomRightDiagonalDimension() {
        return new TopLeftToBottomRightDiagonalDimension();
    }
    private static Dimension TopRightToBottomLeftDiagonalDimension() {
        return new TopRightToBottomLeftDiagonalDimension();
    }

    private static final List<Dimension> AllDimensions =
            Arrays.asList(
                    LeftColumnDimension(),
                    CenterColumnDimension(),
                    RightColumnDimension(),
                    TopRowDimension(),
                    MiddleRowDimension(),
                    BottomRowDimension(),
                    TopLeftToBottomRightDiagonalDimension(),
                    TopRightToBottomLeftDiagonalDimension()
            );

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected GameEndCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        for (Dimension dimension : AllDimensions) {
            if (dimension.isFilledWithMarkOnBoard(mark, board)) {
                return new GameEndConditionWon(dimension);
            }
        }
        return evaluateIfNotPresent.condition();
    }

    private static abstract class Dimension {
        private final List<Position> positions;

        public Dimension(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        private Dimension(List<Position> positions) {
            this.positions = positions;
        }
        
        boolean isFilledWithMarkOnBoard(Mark mark, Board board) {
            return positions.stream().allMatch(position -> board.isPositionOccupiedByMark(position, mark));
        }
    }

    private static class ColumnDimension extends Dimension {
        
        public ColumnDimension(int column) {
            super(new Position(1, column), new Position(2, column), new Position(3, column));
        }
    }

    private static class RowDimension extends Dimension {
        public RowDimension(int row) {
            super(new Position(row, 1), new Position(row, 2), new Position(row, 3));
        }
    }

    private static class TopLeftToBottomRightDiagonalDimension extends Dimension {

        public TopLeftToBottomRightDiagonalDimension() {
            super(new Position(1, 1), new Position(2, 2), new Position(3, 3));
        }
    }

    private static class TopRightToBottomLeftDiagonalDimension extends Dimension {

        public TopRightToBottomLeftDiagonalDimension() {
            super(new Position(1, 3), new Position(2, 2), new Position(3, 1));
        }
    }

    private static class GameEndConditionWon implements GameEndCondition {

        private final Dimension dimension;

        public GameEndConditionWon(Dimension dimension) {
            this.dimension = dimension;
        }

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.won();
        }
    }
}
