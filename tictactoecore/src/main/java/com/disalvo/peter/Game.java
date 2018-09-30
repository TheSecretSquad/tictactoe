package com.disalvo.peter;

public interface Game {
    Game start();

    Game stop();

    Game playMarkAtPosition(Mark mark, Position position);
}
