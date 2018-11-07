package com.disalvo.peter.tictactoe.gameEndEvaluation.rangeOffsetAndLength;

import com.disalvo.peter.tictactoe.Position;

class OffsetRange implements Position.Offset {
    private final int row;
    private final int column;

    public OffsetRange(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position apply(Position position) {
        return position.offsetBy(row, column);
    }
}
