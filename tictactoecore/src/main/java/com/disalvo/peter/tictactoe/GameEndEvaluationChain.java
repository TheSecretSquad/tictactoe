package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;

abstract class GameEndEvaluationChain implements GameEndEvaluation {

    private final GameEndEvaluation evaluateIfNotPresent;

    public GameEndEvaluationChain(GameEndEvaluation evaluateIfNotPresent) {
        this.evaluateIfNotPresent = evaluateIfNotPresent;
    }

    @Override
    public BoardCondition result(Board board, Mark mark) {
        return condition(board, mark, () -> board.evaluationResult(evaluateIfNotPresent, mark));
    }

    protected abstract BoardCondition condition(Board board, Mark mark, NotPresentEvaluation evaluateIfNotPresent);

    @FunctionalInterface
    interface NotPresentEvaluation {
        BoardCondition condition();
    }
}
