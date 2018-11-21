package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.GameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;

public class GameEndEvaluationNone implements GameEndEvaluation {
    @Override
    public GameEndCondition result(Board board, Mark mark, int boardSize) {
        return new BoardConditionNone();
    }

}
