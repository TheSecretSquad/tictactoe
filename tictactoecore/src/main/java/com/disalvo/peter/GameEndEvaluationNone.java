package com.disalvo.peter;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition condition(Board board, Mark mark) {
        return new GameEndConditionNone();
    }

    static class GameEndConditionNone implements GameEndCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
