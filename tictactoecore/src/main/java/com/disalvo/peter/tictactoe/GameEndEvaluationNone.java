package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public BoardCondition result(Board board, Mark mark) {
        return new BoardConditionNone();
    }

    static class BoardConditionNone implements BoardCondition {

        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState;
        }
    }
}
