package com.disalvo.peter.tictactoe;

import java.util.ArrayList;
import java.util.List;
import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;

import static com.disalvo.peter.tictactoe.Board.PositionCollection;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

class GameEndEvaluationWon extends GameEndEvaluationChain {

    private final List<PatternEvaluation> patternEvaluations;

    public GameEndEvaluationWon(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
        this.patternEvaluations = new ArrayList<>();
        patternEvaluations.add(new PatternEvaluationDimensionColumn());
        patternEvaluations.add(new PatternEvaluationDimensionRow());
    }

    @Override
    protected GameEndCondition result(Board board, Mark mark, int boardSize, NotPresentEvaluation notPresentEvaluation) {

        for(PatternEvaluation evaluation : patternEvaluations) {
            if(board.evaluationResult(evaluation, mark)) {
                return new BoardConditionWon(evaluation);
            }
        }

        return notPresentEvaluation.result();
    }

    interface PatternEvaluation extends BoardEvaluation<Boolean>{

    }

    private static abstract class PatternEvaluationDimension implements PatternEvaluation {

        @Override
        public Boolean result(Board board, Mark mark, int boardSize) {
            for(Range range : dimensionFor(boardSize)) {
                if(board.arePositionsOccupiedByMark(range, mark)) {
                    return true;
                }
            }

            return false;
        }

        protected abstract Dimension dimensionFor(int boardSize);
    }

    private static class PatternEvaluationDimensionColumn extends PatternEvaluationDimension {

        @Override
        protected Dimension dimensionFor(int boardSize) {
            return new Position.ColumnDimension(boardSize);
        }
    }

    private static class PatternEvaluationDimensionRow extends PatternEvaluationDimension {

        @Override
        protected Dimension dimensionFor(int boardSize) {
            return new Position.RowDimension(boardSize);
        }
    }

    private static class BoardConditionWon implements GameEndCondition {

        private final PatternEvaluation patternEvaluation;

        public BoardConditionWon(PatternEvaluation patternEvaluation) {
            this.patternEvaluation = patternEvaluation;
        }

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.won();
        }
    }

    interface Dimension extends Iterable<Range> {

    }
}
