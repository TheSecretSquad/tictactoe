package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface PlayValidator {

    PlayValidator play(Mark mark, Position position, PlayTarget playTarget);
}
