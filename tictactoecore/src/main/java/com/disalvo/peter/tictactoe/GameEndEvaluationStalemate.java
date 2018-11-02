package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;

class GameEndEvaluationStalemate extends GameEndEvaluationChain {

    public GameEndEvaluationStalemate(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public BoardCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent) {
        return board.isFilled() ? new BoardConditionStalemate() : evaluateIfNotPresent.condition();
    }

    private static class BoardConditionStalemate implements BoardCondition {
        @Override
        public TicTacToeState nextState(TicTacToeState ticTacToeState) {
            return ticTacToeState.stalemate();
        }
    }
}
