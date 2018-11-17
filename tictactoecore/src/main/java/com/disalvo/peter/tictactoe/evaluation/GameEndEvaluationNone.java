package com.disalvo.peter.tictactoe.evaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.GameEndEvaluation;

import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition result(Board board, Mark mark, int boardSize) {
        return new BoardConditionNone();
    }

}
