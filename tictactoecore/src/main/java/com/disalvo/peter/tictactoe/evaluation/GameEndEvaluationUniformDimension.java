package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.GameEndCondition;
import com.disalvo.peter.tictactoe.GameEndEvaluation;
import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Range;
import com.disalvo.peter.tictactoe.board.Board;

import java.util.Optional;

public class GameEndEvaluationUniformDimension extends GameEndEvaluationChain {
    public GameEndEvaluationUniformDimension(GameEndEvaluation evaluateIfNotPresent) {
        super(evaluateIfNotPresent);
    }

    @Override
    protected GameEndCondition result(Board board, Mark mark, NotPresentEvaluation notPresentEvaluation) {
        Optional<Range> row  = board.rowRangeFilledWithMark(mark);

        if(row.isPresent())
            return new GameEndConditionWon(row.get());

        Optional<Range> column  = board.columnRangeFilledWithMark(mark);

        if(column.isPresent())
            return new GameEndConditionWon(column.get());

        Optional<Range> diagonal  = board.diagonalRangeFilledWithMark(mark);

        if(diagonal.isPresent())
            return new GameEndConditionWon(diagonal.get());

        return notPresentEvaluation.result();
    }
}
