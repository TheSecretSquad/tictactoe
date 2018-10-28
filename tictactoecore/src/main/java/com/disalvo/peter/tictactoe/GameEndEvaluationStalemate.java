package com.disalvo.peter.tictactoe;

class GameEndEvaluationStalemate extends GameEndEvaluationChain {

    public GameEndEvaluationStalemate(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public TicTacToeState.PlayState.GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        return grid.isFilled() ? new GameEndConditionStalemate() : evaluateIfNotPresent.condition();
    }

    private static class GameEndConditionStalemate implements TicTacToeState.PlayState.GameEndCondition {
        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.stalemate();
        }
    }
}
