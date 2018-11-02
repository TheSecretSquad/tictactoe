package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    @Override
    public GameEndCondition result(Board board, Mark mark) {
        return condition(board, mark, () -> board.evaluationResult(evaluateIfNotPresent, mark));
    }

    protected abstract GameEndCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition condition();
    }
}
