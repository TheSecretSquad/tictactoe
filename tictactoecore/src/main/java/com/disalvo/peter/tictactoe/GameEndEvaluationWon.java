package com.disalvo.peter.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class GameEndEvaluationWon extends GameEndEvaluationChain implements Grid.Dimensions {
    private static Grid.Dimension LeftColumnDimension() { return new ColumnDimension(1); }
    private static Grid.Dimension CenterColumnDimension() { return new ColumnDimension(2); }
    private static Grid.Dimension RightColumnDimension() { return new ColumnDimension(3); }
    private static Grid.Dimension TopRowDimension() { return new RowDimension(1); }
    private static Grid.Dimension MiddleRowDimension() { return new RowDimension(2); }
    private static Grid.Dimension BottomRowDimension() { return new RowDimension(3); }
    private static Grid.Dimension TopLeftToBottomRightDiagonalDimension() { return new TopLeftToBottomRightDiagonalDimension(); }
    private static Grid.Dimension TopRightToBottomLeftDiagonalDimension() { return new TopRightToBottomLeftDiagonalDimension(); }

    private static final List<Grid.Dimension> AllDimensions =
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
    protected TicTacToeState.PlayState.GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        WinningDimension dimension = grid.firstDimensionFilledWithMarkOrDefault(this, mark, new EmptyDimension());
        return dimension.condition(evaluateIfNotPresent);
    }

    @Override
    public Iterator<Grid.Dimension> iterator() {
        return AllDimensions.iterator();
    }

    public static abstract class WinningDimension implements Grid.Dimension {
        private final List<Position> positions;

        public WinningDimension(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        protected WinningDimension() {
            this(new ArrayList<>());
        }

        private WinningDimension(List<Position> positions) {
            this.positions = positions;
        }

        @Override
        public boolean isFilledWithMarkOnGrid(Mark mark, Grid grid) {
            return positions.stream().allMatch(position -> grid.isPositionOccupiedByMark(position, mark));
        }

        public TicTacToeState.PlayState.GameEndCondition condition(NotPresentEvaluation evaluateIfNotPresent) {
            return new GameEndConditionWon(this);
        }
    }

    private static class ColumnDimension extends WinningDimension {

        public ColumnDimension(int column) {
            super(new Position(1, column), new Position(2, column), new Position(3, column));
        }
    }

    private static class RowDimension extends WinningDimension {
        public RowDimension(int row) {
            super(new Position(row, 1), new Position(row, 2), new Position(row, 3));
        }
    }

    private static class TopLeftToBottomRightDiagonalDimension extends WinningDimension {

        public TopLeftToBottomRightDiagonalDimension() {
            super(new Position(1, 1), new Position(2, 2), new Position(3, 3));
        }
    }

    private static class TopRightToBottomLeftDiagonalDimension extends WinningDimension {

        public TopRightToBottomLeftDiagonalDimension() {
            super(new Position(1, 3), new Position(2, 2), new Position(3, 1));
        }
    }

    private static class GameEndConditionWon implements TicTacToeState.PlayState.GameEndCondition {

        private final Grid.Dimension dimension;

        public GameEndConditionWon(Grid.Dimension dimension) {
            this.dimension = dimension;
        }

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.won();
        }
    }

    private class EmptyDimension extends WinningDimension {
        @Override
        public TicTacToeState.PlayState.GameEndCondition condition(NotPresentEvaluation evaluateIfNotPresent) {
            return evaluateIfNotPresent.condition();
        }
    }
}
