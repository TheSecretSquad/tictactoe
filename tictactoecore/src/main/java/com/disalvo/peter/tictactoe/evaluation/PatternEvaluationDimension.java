package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.board.Board;

import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluation;
import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluationResult;

abstract class PatternEvaluationDimension implements PatternEvaluation {

    @Override
    public PatternEvaluationResult result(Board board, Mark mark, int boardSize) {
        for (Range range : dimensionFor(boardSize)) {
            if (board.arePositionsOccupiedByMark(range, mark)) {
                return new PatternEvaluationResultFound(range);
            }
        }

        return new PatternEvaluationResultNotFound();
    }

    protected abstract Dimension dimensionFor(int boardSize);
}
