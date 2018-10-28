package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition result(Grid grid, Mark mark) {
        return new GameEndConditionNone();
    }

    static class GameEndConditionNone implements GameEndCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
