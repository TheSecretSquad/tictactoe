package com.disalvo.peter;

public interface GameListener {
    GameListener started(Game game);

    GameListener stopped(Game game);

    GameListener playSuccessful(Game game, Mark mark, Position position);

    GameListener invalidPosition(Game game, Position position, Mark mark);

    GameListener invalidMark(Game game, Mark mark);

    GameListener winningPlay(Game game, Mark mark, Position position);

    GameListener stalemate(TicTacToe subject, Mark mark, Position position);
}
