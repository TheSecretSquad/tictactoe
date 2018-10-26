package com.disalvo.peter;
import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition condition(Grid grid, Mark mark) {
        return new GameEndConditionNone();
    }

    static class GameEndConditionNone implements GameEndCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
