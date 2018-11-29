package com.disalvo.peter.tictactoe;

public interface BoardEvaluation {
    BoardCondition result(Board board, Mark mark);
}
