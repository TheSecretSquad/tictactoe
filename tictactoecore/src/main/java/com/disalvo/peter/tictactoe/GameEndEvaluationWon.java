package com.disalvo.peter.tictactoe;

import java.util.ArrayList;
import java.util.List;
import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

class GameEndEvaluationWon extends GameEndEvaluationChain {

    private static List<DimensionEvaluation> allDimensions(int dimension) {
        List<DimensionEvaluation> dimensions = new ArrayList<>();
        for(int dim = 1; dim <= dimension; dim++) {
            dimensions.add(new DimensionEvaluationColumn(dim));
            dimensions.add(new DimensionEvaluationRow(dim));
        }

        dimensions.add(new DimensionEvaluationTopRightToBottomLeftDiagonal());
        dimensions.add(new DimensionEvaluationTopLeftToBottomRightDiagonal());
        return dimensions;
    }

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected GameEndCondition result(Board board, Mark mark, int dimension, NotPresentEvaluation notPresentEvaluation) {
        for (DimensionEvaluation dimensionEvaluation : allDimensions(dimension)) {
            if (board.evaluationResult(dimensionEvaluation, mark)) {
                return new BoardConditionWon(dimensionEvaluation);
            }
        }
        return notPresentEvaluation.result();
    }

    private static abstract class DimensionEvaluation implements BoardEvaluation<Boolean> {

        @Override
        public Boolean result(Board board, Mark mark, int dimension) {
            return positions(dimension).stream().allMatch(position -> board.isPositionOccupiedByMark(position, mark));
        }

        protected abstract List<Position> positions(int dimension);
    }

    private static abstract class DimensionEvaluationColumnOrRow extends DimensionEvaluation {

        @Override
        protected List<Position> positions(int dimension) {
            List<Position> positions = new ArrayList<>(dimension);
            for(int rowOrColumn = 1; rowOrColumn <= dimension; rowOrColumn++) {
                positions.add(position(rowOrColumn));
            }
            return positions;
        }

        protected abstract Position position(int rowOrColumn);
    }

    private static class DimensionEvaluationColumn extends DimensionEvaluationColumnOrRow {
        private final int column;

        public DimensionEvaluationColumn(int column) {
            this.column = column;
        }

        @Override
        protected Position position(int rowOrColumn) {
            return new Position(rowOrColumn, column);
        }
    }

    private static class DimensionEvaluationRow extends DimensionEvaluationColumnOrRow {
        private final int row;

        public DimensionEvaluationRow(int row) {
            this.row = row;
        }

        @Override
        protected Position position(int rowOrColumn) {
            return new Position(row, rowOrColumn);
        }
    }

    private static class DimensionEvaluationTopLeftToBottomRightDiagonal extends DimensionEvaluation {

        @Override
        protected List<Position> positions(int dimension) {
            List<Position> positions = new ArrayList<>(dimension);
            for(int dim = 1; dim <= dimension; dim++) {
                positions.add(new Position(dim, dim));
            }
            return positions;
        }
    }

    private static class DimensionEvaluationTopRightToBottomLeftDiagonal extends DimensionEvaluation {

        @Override
        protected List<Position> positions(int dimension) {
            List<Position> positions = new ArrayList<>(dimension);
            for(int row = 1, column = dimension; row <= dimension && column >= 1; row++, column--) {
                positions.add(new Position(row, column));
            }
            return positions;
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
