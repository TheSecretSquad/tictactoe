package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.*;

public class BoardEvaluationUniformDimension extends BoardEvaluationChain {

    public BoardEvaluationUniformDimension(BoardEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected BoardCondition result(Board board, Mark mark, BoardEvaluation evaluateIfNotPresent) {
        return new BoardEvaluationRows(
                new BoardEvaluationColumns(
                        new BoardEvaluationDiagonals(evaluateIfNotPresent))).result(board, mark);
    }

    private static abstract class BoardEvaluationDimension extends BoardEvaluationChain {

        public BoardEvaluationDimension(BoardEvaluation evaluateIfNotPresent) {
            super(evaluateIfNotPresent);
        }

        @Override
        protected BoardCondition result(Board board, Mark mark, BoardEvaluation evaluateIfNotPresent) {
            return board.positionCollectionFilledWithMark(
                    positionCollectionGroup(board),
                    mark,
                    () -> evaluateIfNotPresent.result(board, mark),
                    (PositionCollection range) -> new BoardConditionWon(range)
            );
        }

        protected abstract PositionCollectionGroup positionCollectionGroup(Board board);
    }

    private static class BoardEvaluationRows extends BoardEvaluationDimension {

        public BoardEvaluationRows(BoardEvaluation evaluateIfNotPresent) {
            super(evaluateIfNotPresent);
        }

        @Override
        protected PositionCollectionGroup positionCollectionGroup(Board board) {
            return board.rows();
        }
    }

    private static class BoardEvaluationColumns extends BoardEvaluationDimension {

        public BoardEvaluationColumns(BoardEvaluation evaluateIfNotPresent) {
            super(evaluateIfNotPresent);
        }

        @Override
        protected PositionCollectionGroup positionCollectionGroup(Board board) {
            return board.columns();
        }
    }

    private static class BoardEvaluationDiagonals extends BoardEvaluationDimension {

        public BoardEvaluationDiagonals(BoardEvaluation evaluateIfNotPresent) {
            super(evaluateIfNotPresent);
        }

        @Override
        protected PositionCollectionGroup positionCollectionGroup(Board board) {
            return board.diagonals();
        }
    }
}
