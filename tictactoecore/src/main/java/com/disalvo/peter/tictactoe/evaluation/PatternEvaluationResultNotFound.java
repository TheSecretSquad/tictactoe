package com.disalvo.peter.tictactoe.evaluation;

import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;
import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluationResult;

class PatternEvaluationResultNotFound implements PatternEvaluationResult {

    @Override
    public boolean isFound() {
        return false;
    }

    @Override
    public GameEndCondition condition(GameEndEvaluationChain.NotPresentEvaluation notPresentEvaluation) {
        return notPresentEvaluation.result();
    }
}
