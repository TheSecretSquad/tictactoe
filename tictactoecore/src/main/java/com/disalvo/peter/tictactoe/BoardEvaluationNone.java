package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;
import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;

public class BoardEvaluationNone implements BoardEvaluation{
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
