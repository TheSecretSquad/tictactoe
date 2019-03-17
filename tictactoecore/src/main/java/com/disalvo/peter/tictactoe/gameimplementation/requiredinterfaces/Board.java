package com.disalvo.peter.tictactoe.gameimplementation.requiredinterfaces;

import com.disalvo.peter.tictactoe.stable.Mark;
import com.disalvo.peter.tictactoe.stable.Position;

public interface Board extends PositionValidationRule {

    Board placeMarkAtPosition(Mark mark, Position position);
}
