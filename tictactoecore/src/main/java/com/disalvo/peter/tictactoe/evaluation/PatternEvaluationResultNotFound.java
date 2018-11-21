package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;

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
