package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;

abstract class PatternEvaluationDimension implements GameEndEvaluationWon.PatternEvaluation {

    @Override
    public GameEndEvaluationWon.PatternEvaluationResult result(Board board, Mark mark, int boardSize) {
        for(Range range : dimensionFor(boardSize)) {
            if(board.arePositionsOccupiedByMark(range, mark)) {
                return new PatternEvaluationResultFound(range);
            }
        }

        return new PatternEvaluationResultNotFound();
    }

    protected abstract Dimension dimensionFor(int boardSize);
}
