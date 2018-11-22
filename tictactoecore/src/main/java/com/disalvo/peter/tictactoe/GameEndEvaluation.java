package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.board.Board;

public interface GameEndEvaluation {
    GameEndCondition result(Board board, Mark mark);
}
