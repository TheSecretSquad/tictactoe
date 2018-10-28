package com.disalvo.peter.tictactoe;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    protected abstract TicTacToeState.PlayState.GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @Override
    public TicTacToeState.PlayState.GameEndCondition result(Grid grid, Mark mark) {
        return condition(grid, mark, () -> evaluateIfNotPresent.result(grid, mark));
    }

    @FunctionalInterface
    interface NotPresentEvaluation {
        TicTacToeState.PlayState.GameEndCondition condition();
    }
}
