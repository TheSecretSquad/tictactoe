package com.disalvo.peter.tictactoe.evaluation.range;

import com.disalvo.peter.tictactoe.Position;

public class Offset {
    private final int row;
    private final int column;

    public Offset(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Position apply(Position position) {
        return position.offsetBy(row, column);
    }
}
