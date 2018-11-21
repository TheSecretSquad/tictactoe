package com.disalvo.peter.tictactoe;

public interface Game {
    Game start();

    Game stop();

    Game playMarkAtPosition(Mark mark, Position position);

    Game printOn(GameMedia gameMedia);
}
