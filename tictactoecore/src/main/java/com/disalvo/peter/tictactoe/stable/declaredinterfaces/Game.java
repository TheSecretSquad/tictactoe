package com.disalvo.peter.tictactoe.stable.declaredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface Game {

    Game start();

    Game stop();

    Game submitMarkAtPosition(Mark mark, Position position);
}
