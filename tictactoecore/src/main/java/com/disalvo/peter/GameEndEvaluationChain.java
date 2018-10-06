package com.disalvo.peter;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    @Override
    public final GameEndCondition condition(Board board, Mark mark) {
        return condition(board, mark, () -> evaluateIfNotPresent.condition(board, mark));
    }

    protected abstract GameEndCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @FunctionalInterface
    interface NotPresentEvaluation {
        GameEndCondition condition();
    }
}
