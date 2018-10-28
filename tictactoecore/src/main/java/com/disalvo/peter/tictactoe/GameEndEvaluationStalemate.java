package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

class GameEndEvaluationStalemate extends GameEndEvaluationChain {

    public GameEndEvaluationStalemate(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public GameEndCondition condition(Grid grid, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        return grid.isFilled() ? new GameEndConditionStalemate() : evaluateIfNotPresent.condition();
    }

    private static class GameEndConditionStalemate implements GameEndCondition {
        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.stalemate();
        }
    }
}
