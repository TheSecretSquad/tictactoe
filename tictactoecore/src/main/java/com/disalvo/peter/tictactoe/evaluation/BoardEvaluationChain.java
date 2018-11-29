package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Board;
import com.disalvo.peter.tictactoe.BoardCondition;
import com.disalvo.peter.tictactoe.BoardEvaluation;
import com.disalvo.peter.tictactoe.Mark;

abstract class BoardEvaluationChain implements BoardEvaluation {

    private final BoardEvaluation evaluateIfNotPresent;

    public BoardEvaluationChain(BoardEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    @Override
    public BoardCondition result(Board board, Mark mark) {
        return result(board, mark, evaluateIfNotPresent);
    }

    protected abstract BoardCondition result(Board board, Mark mark, BoardEvaluation evaluateIfNotPresent);
}
