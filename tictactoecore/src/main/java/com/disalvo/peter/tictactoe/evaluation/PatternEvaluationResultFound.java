package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.TicTacToeState;
import com.disalvo.peter.tictactoe.board.PositionCollection;

class PatternEvaluationResultFound implements GameEndEvaluationWon.PatternEvaluationResult {

    private final PositionCollection positionCollection;

    public PatternEvaluationResultFound(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public boolean isFound() {
        return true;
    }

    @Override
    public TicTacToeState.PlayState.GameEndCondition condition(GameEndEvaluationChain.NotPresentEvaluation notPresentEvaluation) {
        return new BoardConditionWon(positionCollection);
    }
}
