package com.disalvo.peter.tictactoe;

import com.disalvo.peter.tictactoe.Mark;
import com.disalvo.peter.tictactoe.Position;

public interface Game {
    Game start();

    Game stop();

    Game playMarkAtPosition(Mark mark, Position position);

    Game printOn(GameMedia gameMedia);

    interface GameMedia {

    }
}
