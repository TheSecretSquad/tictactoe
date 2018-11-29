package com.disalvo.peter.tictactoe;

public interface BoardMedia {

    BoardMedia printMarkAtPosition(Mark mark, Position position);

    BoardMedia printEmptyPosition(Position position);
}
