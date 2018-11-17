package com.disalvo.peter.tictactoe.evaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.GameEndEvaluation;

import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluationIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluationIfNotPresent) {
        this.evaluationIfNotPresent = evaluationIfNotPresent;
    }

    @Override
    public GameEndCondition result(Board board, Mark mark, int boardSize) {
        return result(board, mark, boardSize, () -> board.evaluationResult(evaluationIfNotPresent, mark));
    }

    protected abstract GameEndCondition result(Board board, Mark mark, int boardSize, NotPresentEvaluation notPresentEvaluation);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition result();
    }
}
