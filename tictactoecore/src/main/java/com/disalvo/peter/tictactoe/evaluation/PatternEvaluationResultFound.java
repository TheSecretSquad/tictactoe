package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.board.PositionCollection;
import static com.disalvo.peter.tictactoe.PlayState.GameEndCondition;
import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluationResult;

class PatternEvaluationResultFound implements PatternEvaluationResult {

    private final PositionCollection positionCollection;

    public PatternEvaluationResultFound(PositionCollection positionCollection) {
        this.positionCollection = positionCollection;
    }

    @Override
    public boolean isFound() {
        return true;
    }

    @Override
    public GameEndCondition condition(GameEndEvaluationChain.NotPresentEvaluation notPresentEvaluation) {
        return new BoardConditionWon(positionCollection);
    }
}
