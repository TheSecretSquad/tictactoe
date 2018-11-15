package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.TicTacToeState;

class PatternEvaluationResultNotFound implements GameEndEvaluationWon.PatternEvaluationResult {

    @Override
    public boolean isFound() {
        return false;
    }

    @Override
    public TicTacToeState.PlayState.GameEndCondition condition(GameEndEvaluationChain.NotPresentEvaluation notPresentEvaluation) {
        return notPresentEvaluation.result();
    }
}
