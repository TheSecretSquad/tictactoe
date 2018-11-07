package com.disalvo.peter.tictactoe.board;

import com.disalvo.peter.tictactoe.Mark;

public interface BoardEvaluation<T> {

    T result(Board board, Mark mark, int boardSize);
}
