package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition result(Board board, Mark mark, int boardSize) {
        return new BoardConditionNone();
    }

    static class BoardConditionNone implements GameEndCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
