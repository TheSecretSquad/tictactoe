package com.disalvo.peter.tictactoe;

public interface GameListener {
    GameListener started(Game game);

    GameListener stopped(Game game);

    GameListener invalidPosition(Game game, Position position, Mark mark);

    GameListener invalidMark(Game game, Mark mark);

    GameListener continuePlay(Game game, Mark mark, Position position);

    GameListener winningPlay(Game game, Mark mark, Position position);

    GameListener stalemate(Game game, Mark mark, Position position);
}
