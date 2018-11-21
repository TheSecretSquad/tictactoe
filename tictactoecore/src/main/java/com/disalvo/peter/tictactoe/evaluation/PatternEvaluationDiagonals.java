package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;
import com.disalvo.peter.tictactoe.board.Board;
import com.disalvo.peter.tictactoe.evaluation.range.Offset;
import com.disalvo.peter.tictactoe.evaluation.range.RangeOffsetAndLength;

import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluation;
import static com.disalvo.peter.tictactoe.evaluation.GameEndEvaluationWon.PatternEvaluationResult;

class PatternEvaluationDiagonals implements PatternEvaluation {

    @Override
    public PatternEvaluationResult result(Board board, Mark mark, int boardSize) {
        Range topLeftToBottomRight = new RangeOffsetAndLength(new Position(1, 1), new Offset(1, 1), boardSize);
        Range topRightToBottomLeft = new RangeOffsetAndLength(new Position(1, boardSize), new Offset(1, -1), boardSize);

        if (board.arePositionsOccupiedByMark(topLeftToBottomRight, mark)) {
            return new PatternEvaluationResultFound(topLeftToBottomRight);
        }

        if (board.arePositionsOccupiedByMark(topRightToBottomLeft, mark)) {
            return new PatternEvaluationResultFound(topRightToBottomLeft);
        }

        return new PatternEvaluationResultNotFound();
    }
}
