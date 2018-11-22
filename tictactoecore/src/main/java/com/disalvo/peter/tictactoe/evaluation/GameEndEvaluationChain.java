package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.GameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluationIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluationIfNotPresent) {
        this.evaluationIfNotPresent = evaluationIfNotPresent;
    }

    @Override
    public GameEndCondition result(Board board, Mark mark) {
        return result(board, mark, () -> evaluationIfNotPresent.result(board, mark));
    }

    protected abstract GameEndCondition result(Board board, Mark mark, NotPresentEvaluation notPresentEvaluation);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition result();
    }
}
