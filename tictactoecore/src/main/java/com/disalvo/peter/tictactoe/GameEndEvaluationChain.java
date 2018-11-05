package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluationIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluationIfNotPresent) {
        this.evaluationIfNotPresent = evaluationIfNotPresent;
    }

    @Override
    public GameEndCondition result(Board board, Mark mark, int dimension) {
        return result(board, mark, dimension, () -> board.evaluationResult(evaluationIfNotPresent, mark));
    }

    protected abstract GameEndCondition result(Board board, Mark mark, int dimension, NotPresentEvaluation notPresentEvaluation);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition result();
    }
}
