package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    protected abstract GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @Override
    public GameEndCondition result(Grid grid, Mark mark) {
        return condition(grid, mark, () -> evaluateIfNotPresent.result(grid, mark));
    }

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition condition();
    }
}
