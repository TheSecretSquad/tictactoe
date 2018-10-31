package com.disalvo.peter.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;
import static com.disalvo.peter.tictactoe.Grid.Dimension;
import static com.disalvo.peter.tictactoe.Grid.Dimensions;

class GameEndEvaluationWon extends GameEndEvaluationChain implements Dimensions {
    private static Dimension LeftColumn() { return new DimensionColumn(1); }
    private static Dimension CenterColumn() { return new DimensionColumn(2); }
    private static Dimension RightColumn() { return new DimensionColumn(3); }
    private static Dimension TopRow() { return new DimensionRow(1); }
    private static Dimension MiddleRow() { return new DimensionRow(2); }
    private static Dimension BottomRow() { return new DimensionRow(3); }
    private static Dimension TopLeftToBottomRightDiagonal() { return new DimensionTopLeftToBottomRightDiagonal(); }
    private static Dimension TopRightToBottomLeftDiagonal() { return new DimensionTopRightToBottomLeftDiagonal(); }

    private static final List<Dimension> AllDimensions =
            Arrays.asList(
                    LeftColumn(), CenterColumn(), RightColumn(),
                    TopRow(), MiddleRow(), BottomRow(),
                    TopLeftToBottomRightDiagonal(), TopRightToBottomLeftDiagonal()
            );

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        DimensionWinning dimension = grid.firstDimensionFilledWithMarkOrDefault(this, mark, new DimensionWinningNotFound());
        return dimension.condition(evaluateIfNotPresent);
    }

    @Override
    public Iterator<Dimension> iterator() {
        return AllDimensions.iterator();
    }

    public static abstract class DimensionWinning implements Dimension {
        private final List<Position> positions;

        public DimensionWinning(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        protected DimensionWinning() {
            this(new ArrayList<>());
        }

        private DimensionWinning(List<Position> positions) {
            this.positions = positions;
        }

        @Override
        public boolean isFilledWithMarkOnGrid(Mark mark, Grid grid) {
            return positions.stream().allMatch(position -> grid.isPositionOccupiedByMark(position, mark));
        }

        public GameEndCondition condition(NotPresentEvaluation evaluateIfNotPresent) {
            return new GameEndConditionWon(this);
        }
    }

    private static class DimensionColumn extends DimensionWinning {

        public DimensionColumn(int column) {
            super(new Position(1, column), new Position(2, column), new Position(3, column));
        }
    }

    private static class DimensionRow extends DimensionWinning {
        public DimensionRow(int row) {
            super(new Position(row, 1), new Position(row, 2), new Position(row, 3));
        }
    }

    private static class DimensionTopLeftToBottomRightDiagonal extends DimensionWinning {

        public DimensionTopLeftToBottomRightDiagonal() {
            super(new Position(1, 1), new Position(2, 2), new Position(3, 3));
        }
    }

    private static class DimensionTopRightToBottomLeftDiagonal extends DimensionWinning {

        public DimensionTopRightToBottomLeftDiagonal() {
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

    private class DimensionWinningNotFound extends DimensionWinning {
        @Override
        public GameEndCondition condition(NotPresentEvaluation evaluateIfNotPresent) {
            return evaluateIfNotPresent.condition();
        }
    }
}
