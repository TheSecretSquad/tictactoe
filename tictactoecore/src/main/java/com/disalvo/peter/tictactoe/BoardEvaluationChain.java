package com.disalvo.peter.tictactoe;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.BoardCondition;
import static com.disalvo.peter.tictactoe.Board.BoardEvaluation;

abstract class BoardEvaluationChain implements BoardEvaluation {

    private final BoardEvaluation evaluateIfNotPresent;

    public BoardEvaluationChain(BoardEvaluation evaluateIfNotPresent) {
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
