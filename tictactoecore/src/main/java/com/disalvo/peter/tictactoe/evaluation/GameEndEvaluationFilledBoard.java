package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.GameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;

public class GameEndEvaluationFilledBoard extends GameEndEvaluationChain {

    public GameEndEvaluationFilledBoard(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    public GameEndCondition result(Board board, Mark mark, NotPresentEvaluation notPresentEvaluation) {
        return board.isFilled() ? new GameEndConditionStalemate() : notPresentEvaluation.result();
    }
}
