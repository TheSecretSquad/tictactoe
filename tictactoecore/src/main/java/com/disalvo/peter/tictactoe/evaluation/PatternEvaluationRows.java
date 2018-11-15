package com.disalvo.peter.tictactoe.evaluation;

import com.disalvo.peter.tictactoe.evaluation.dimension.DimensionRows;

class PatternEvaluationRows extends PatternEvaluationDimension {

    @Override
    protected Dimension dimensionFor(int boardSize) {
        return new DimensionRows(boardSize);
    }
}
