package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Board;
import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardEvaluation;
import com.disalvo.peter.tictactoe.Mark;

public class BoardEvaluationNone implements BoardEvaluation {
    @Override
    public BoardCondition result(Board board, Mark mark) {
        return new BoardConditionNone();
    }
}
