package com.disalvo.peter;

import static com.disalvo.peter.TicTacToeState.PlayState.GameEndCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    @Override
    public final GameEndCondition condition(Grid grid, Mark mark) {
        return condition(grid, mark, () -> evaluateIfNotPresent.condition(grid, mark));
    }

    protected abstract GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition condition();
    }
}
