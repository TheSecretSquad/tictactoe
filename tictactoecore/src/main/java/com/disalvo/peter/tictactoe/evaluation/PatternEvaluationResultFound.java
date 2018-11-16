package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.board.PositionCollection;
import static com.disalvo.peter.tictactoe.TicTacToeState.PlayState.GameEndCondition;

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
    public GameEndCondition condition(GameEndEvaluationChain.NotPresentEvaluation notPresentEvaluation) {
        return new BoardConditionWon(positionCollection);
    }
}
