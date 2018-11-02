package com.disalvo.peter.tictactoe;

import java.util.Arrays;
import java.util.List;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

class GameEndEvaluationWon extends GameEndEvaluationChain {
    private static DimensionEvaluation LeftColumn() { return new DimensionEvaluationColumn(1); }
    private static DimensionEvaluation CenterColumn() { return new DimensionEvaluationColumn(2); }
    private static DimensionEvaluation RightColumn() { return new DimensionEvaluationColumn(3); }
    private static DimensionEvaluation TopRow() { return new DimensionEvaluationRow(1); }
    private static DimensionEvaluation MiddleRow() { return new DimensionEvaluationRow(2); }
    private static DimensionEvaluation BottomRow() { return new DimensionEvaluationRow(3); }
    private static DimensionEvaluation TopLeftToBottomRightDiagonal() { return new DimensionEvaluationTopLeftToBottomRightDiagonal(); }
    private static DimensionEvaluation TopRightToBottomLeftDiagonal() { return new DimensionEvaluationTopRightToBottomLeftDiagonal(); }

    private static final List<DimensionEvaluation> AllDimensions =
            Arrays.asList(
                    LeftColumn(), CenterColumn(), RightColumn(),
                    TopRow(), MiddleRow(), BottomRow(),
                    TopLeftToBottomRightDiagonal(), TopRightToBottomLeftDiagonal()
            );

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected GameEndCondition result(Board board, Mark mark, NotPresentEvaluation notPresentEvaluation) {
        for(DimensionEvaluation dimensionEvaluation : AllDimensions) {
            if(board.evaluationResult(dimensionEvaluation, mark)) {
                return new BoardConditionWon(dimensionEvaluation);
            }
        }
        return notPresentEvaluation.result();
    }

    public static class DimensionEvaluation implements Board.BoardEvaluation<Boolean> {
        private final List<Position> positions;

        public DimensionEvaluation(Position position1, Position position2, Position position3) {
            this(Arrays.asList(position1, position2, position3));
        }

        private DimensionEvaluation(List<Position> positions) {
            this.positions = positions;
        }

        @Override
        public Boolean result(Board board, Mark mark) {
            return positions.stream().allMatch(position -> board.isPositionOccupiedByMark(position, mark));
        }
    }

    private static class DimensionEvaluationColumn extends DimensionEvaluation {

        public DimensionEvaluationColumn(int column) {
            super(new Position(1, column), new Position(2, column), new Position(3, column));
        }
    }

    private static class DimensionEvaluationRow extends DimensionEvaluation {
        public DimensionEvaluationRow(int row) {
            super(new Position(row, 1), new Position(row, 2), new Position(row, 3));
        }
    }

    private static class DimensionEvaluationTopLeftToBottomRightDiagonal extends DimensionEvaluation {

        public DimensionEvaluationTopLeftToBottomRightDiagonal() {
            super(new Position(1, 1), new Position(2, 2), new Position(3, 3));
        }
    }

    private static class DimensionEvaluationTopRightToBottomLeftDiagonal extends DimensionEvaluation {

        public DimensionEvaluationTopRightToBottomLeftDiagonal() {
            super(new Position(1, 3), new Position(2, 2), new Position(3, 1));
        }
    }

    private static class BoardConditionWon implements GameEndCondition {

        private final DimensionEvaluation dimensionEvaluation;

        public BoardConditionWon(DimensionEvaluation dimensionEvaluation) {
            this.dimensionEvaluation = dimensionEvaluation;
        }

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.won();
        }
    }
}
