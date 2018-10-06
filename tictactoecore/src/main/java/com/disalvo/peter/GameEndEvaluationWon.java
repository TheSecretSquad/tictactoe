package com.disalvo.peter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

class GameEndEvaluationWon extends GameEndEvaluationChain {
    private static final Dimension LeftColumnDimension() { return ColumnDimension(1); }
    private static final Dimension CenterColumnDimension() { return ColumnDimension(2); }
    private static final Dimension RightColumnDimension() { return ColumnDimension(3); }
    private static final Dimension TopRowDimension() { return RowDimension(1); }
    private static final Dimension MiddleRowDimension() { return RowDimension(2); }
    private static final Dimension BottomRowDimension() { return RowDimension(3); }
    private static Dimension TopLeftToBottomRightDiagonalDimension() {
        return new Dimension(new Position(1, 1), new Position(2, 2), new Position(3, 3));
    }
    private static Dimension TopRightToBottomLeftDiagonalDimension() {
        return new Dimension(new Position(1, 3), new Position(2, 2), new Position(3, 1));
    }
    private static Dimension ColumnDimension(int column) {
        return new Dimension(new Position(1, column), new Position(2, column), new Position(3, column));
    }

    private static Dimension RowDimension(int row) {
        return new Dimension(new Position(row, 1), new Position(row, 2), new Position(row, 3));
    }

    private static List<Dimension> AllDimensions() {
        return Arrays.asList(
                LeftColumnDimension(),
                CenterColumnDimension(),
                RightColumnDimension(),
                TopRowDimension(),
                MiddleRowDimension(),
                BottomRowDimension(),
                TopLeftToBottomRightDiagonalDimension(),
                TopRightToBottomLeftDiagonalDimension()
        );
    }
    private static final List<Dimension> AllDimensions = AllDimensions();

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

    private static class Dimension {
        private final List<Position> positions;

        public Dimension(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        private Dimension(List<Position> positions) {
            this.positions = positions;
        }

        public boolean isFilledWithMarkOnBoard(Mark mark, Board board) {
            return positions.stream().allMatch(position -> board.isPositionOccupiedByMark(position, mark));
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
