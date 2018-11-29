package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Board;
import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardEvaluation;
import com.disalvo.peter.tictactoe.Mark;

public class BoardEvaluationFilled extends BoardEvaluationChain {

    public BoardEvaluationFilled(BoardEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public BoardCondition result(Board board, Mark mark, BoardEvaluation evaluateIfNotPresent) {
        return board.isFilled() ? new BoardConditionStalemate() : evaluateIfNotPresent.result(board, mark);
    }
}
