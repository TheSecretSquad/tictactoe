package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.evaluation.dimension.DimensionColumns;

class PatternEvaluationColumns extends PatternEvaluationDimension {

    @Override
    protected Dimension dimensionFor(int boardSize) {
        return new DimensionColumns(boardSize);
    }
}
