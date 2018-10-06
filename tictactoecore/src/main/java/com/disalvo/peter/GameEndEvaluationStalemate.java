package com.disalvo.peter;

class GameEndEvaluationStalemate extends GameEndEvaluationChain {

    public GameEndEvaluationStalemate(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public GameEndCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        return board.isFilled() ? new GameEndConditionStalemate() : evaluateIfNotPresent.condition();
    }

    private static class GameEndConditionStalemate implements GameEndCondition {
        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.stalemate();
        }
    }
}
