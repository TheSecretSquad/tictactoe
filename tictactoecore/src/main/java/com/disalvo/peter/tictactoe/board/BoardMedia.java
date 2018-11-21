package com.disalvo.peter.tictactoe.board;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;

public interface BoardMedia {

    BoardMedia printMarkAtPosition(Mark mark, Position position);

    BoardMedia printEmptyPosition(Position position);
}
