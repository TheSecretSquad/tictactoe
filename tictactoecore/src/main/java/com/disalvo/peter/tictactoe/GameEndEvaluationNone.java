package com.disalvo.peter.tictactoe;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public TicTacToeState.PlayState.GameEndCondition result(Grid grid, Mark mark) {
        return new GameEndConditionNone();
    }

    static class GameEndConditionNone implements TicTacToeState.PlayState.GameEndCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
