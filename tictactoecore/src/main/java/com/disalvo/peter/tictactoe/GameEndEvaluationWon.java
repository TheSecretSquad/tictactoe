package com.disalvo.peter.tictactoe;

import java.util.ArrayList;
import java.util.Iterator;
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
        PatternEvaluationResult patternEvaluationResult = new PatternEvaluationResultNotFound();
        Iterator<PatternEvaluation> patternEvaluationIterator = patternEvaluations.iterator();

        while(!patternEvaluationResult.isFound() && patternEvaluationIterator.hasNext()) {
            PatternEvaluation patternEvaluation = patternEvaluationIterator.next();
            patternEvaluationResult = board.evaluationResult(patternEvaluation, mark);
        }

        return patternEvaluationResult.condition(notPresentEvaluation);
    }

    interface PatternEvaluation extends BoardEvaluation<PatternEvaluationResult>{

    }

    private interface PatternEvaluationResult {

        boolean isFound();

        GameEndCondition condition(NotPresentEvaluation notPresentEvaluation);
    }

    private static class PatternEvaluationResultFound implements PatternEvaluationResult {

        private final PositionCollection positionCollection;

        public PatternEvaluationResultFound(PositionCollection positionCollection) {
            this.positionCollection = positionCollection;
        }

        @Override
        public boolean isFound() {
            return true;
        }

        @Override
        public GameEndCondition condition(NotPresentEvaluation notPresentEvaluation) {
            return new BoardConditionWon(positionCollection);
        }
    }

    private static class PatternEvaluationResultNotFound implements PatternEvaluationResult {

        @Override
        public boolean isFound() {
            return false;
        }

        @Override
        public GameEndCondition condition(NotPresentEvaluation notPresentEvaluation) {
            return notPresentEvaluation.result();
        }
    }

    private static abstract class PatternEvaluationDimension implements PatternEvaluation {

        @Override
        public PatternEvaluationResult result(Board board, Mark mark, int boardSize) {
            for(Range range : dimensionFor(boardSize)) {
                if(board.arePositionsOccupiedByMark(range, mark)) {
                    return new PatternEvaluationResultFound(range);
                }
            }

            return new PatternEvaluationResultNotFound();
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

        private final PositionCollection positionCollection;

        public BoardConditionWon(PositionCollection positionCollection) {
            this.positionCollection = positionCollection;
        }

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.won();
        }
    }

    interface Dimension extends Iterable<Range> {

    }
}
