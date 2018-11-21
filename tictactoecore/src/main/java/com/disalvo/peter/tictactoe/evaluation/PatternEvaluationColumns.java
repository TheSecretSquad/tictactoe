package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.Dimension;
import com.disalvo.peter.tictactoe.dimension.DimensionColumns;

class PatternEvaluationColumns extends PatternEvaluationDimension {

    @Override
    protected Dimension dimensionFor(int boardSize) {
        return new DimensionColumns(boardSize);
    }
}
